package com.examplenewstack.newstack.domain.loan.service;

import com.examplenewstack.newstack.config.rabbitMQ.RabbitMQConfig;
import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.repository.BookRepository;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import com.examplenewstack.newstack.domain.loan.Loan;
import com.examplenewstack.newstack.domain.loan.dto.LoanNotificationDTO;
import com.examplenewstack.newstack.domain.loan.dto.LoanRequest;
import com.examplenewstack.newstack.domain.loan.dto.LoanResponse;
import com.examplenewstack.newstack.domain.loan.enums.StatusLoan;
import com.examplenewstack.newstack.domain.loan.exception.NoLoanFoundException;
import com.examplenewstack.newstack.domain.loan.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoanCrudService {

    private final ApplicationContext applicationContext;
    private final LoanRepository repository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;
    private final RabbitTemplate rabbitTemplate;

    public LoanCrudService(ApplicationContext applicationContext, LoanRepository repository, ClientRepository clientRepository,
                           BookRepository bookRepository, RabbitTemplate rabbitTemplate) {
        this.applicationContext = applicationContext;
        this.repository = repository;
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    private static final int MAX_ACTIVE_LOANS_PER_CLIENT = 3;
    private static final int MAX_COPIES_OF_SAME_BOOK_PER_CLIENT = 1;

    @Transactional
    public Loan register(LoanRequest loanRequest) throws Exception {
        Client clientFound = clientRepository.findById(loanRequest.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
        Book bookFound = bookRepository.findById(loanRequest.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

//        if (bookFound.getDisponibility_quantity() <= 0) {
//            throw new Exception("Não há exemplares deste livro disponíveis para empréstimo.");
//        }

        List<Loan> activeLoans = repository.findActiveLoansByClientId(loanRequest.getClientId());
        if (activeLoans.size() >= MAX_ACTIVE_LOANS_PER_CLIENT) {
            throw new Exception("Cliente atingiu o limite máximo de " + MAX_ACTIVE_LOANS_PER_CLIENT + " empréstimos ativos!");
        }

        long countSameBook = activeLoans.stream()
                .filter(loan -> loan.getBook() != null && Objects.equals(loan.getBook().getId(), loanRequest.getBookId()))
                .count();

        if (countSameBook >= MAX_COPIES_OF_SAME_BOOK_PER_CLIENT) {
            throw new Exception("Este cliente já possui um empréstimo ativo para este Livro!");
        }

        Loan loanToSave = new Loan();
        loanToSave.setClient(clientFound);
        loanToSave.setBook(bookFound);

        if (bookFound.getDisponibility_quantity() <= 0) {
            loanToSave.setStatus(StatusLoan.RESERVADO);
        } else {
            loanToSave.setStatus(StatusLoan.EMPRESTADO);
            bookFound.setDisponibility_quantity(bookFound.getDisponibility_quantity() - 1);
        }

        bookRepository.save(bookFound);

        LocalDate loanDate = LocalDate.now();
        LocalDate expectedReturnDate = loanDate.plusDays(loanRequest.getLoanTermDays());

        loanToSave.setLoanDate(loanDate.atStartOfDay());
        loanToSave.setExpectedReturnDate(expectedReturnDate.atStartOfDay());

        loanToSave.setBookTitle(bookFound.getTitle());

        Loan newLoan = repository.save(loanToSave);

        LoanNotificationDTO notificationDTO = new LoanNotificationDTO(
                clientFound.getName(),
                clientFound.getEmail(),
                bookFound.getTitle(),
                newLoan.getStatus(),
                newLoan.getLoanDate(),
                newLoan.getExpectedReturnDate()
        );

        String routingKey = "loan.status." + newLoan.getStatus().name().toLowerCase();
        rabbitTemplate.convertAndSend(RabbitMQConfig.LOAN_EXCHANGE_NAME, routingKey, notificationDTO);

        return newLoan;
    }

    @Transactional(readOnly = true)
    public List<LoanResponse> reportAll() {
        return repository.findAll().stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //Função responsavel por mostrar os empréstimos com paginação

    public Page<LoanResponse> getFilteredLoans(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        Page<Loan> foundLoans = repository.findAll(pageRequest);
        return foundLoans.map(LoanResponse::fromEntity);
    }

    @Transactional(readOnly = true)
    public List<LoanResponse> reportByClientId(Integer clientId) {
        List<Loan> loans = repository.findByClientId(clientId);
        if (loans.isEmpty()) {
            throw new NoLoanFoundException("Nenhum empréstimo encontrado para este cliente.");
        }

        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(int loanId) {
        Loan loanToDelete = repository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo com ID " + loanId + " não encontrado."));

        Book book = loanToDelete.getBook();
        if (book != null) {
            book.setDisponibility_quantity(book.getDisponibility_quantity() + 1);
            bookRepository.save(book);
        }

        repository.delete(loanToDelete);
    }

    @Transactional
    public void confirmReturn(int loanId) {
        Loan loan = repository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo com ID " + loanId + " não encontrado."));

        // Altera o status e data de devolução
        loan.setStatus(StatusLoan.DEVOLVIDO);
        loan.setActualReturnDate(LocalDateTime.now());

        // Devolve o livro ao estoque
        Book book = loan.getBook();
        if (book != null) {
            book.setDisponibility_quantity(book.getDisponibility_quantity() + 1);
            bookRepository.save(book);
        }

        repository.save(loan);

        // Prepara e envia a notificação para a fila
        LoanNotificationDTO notificationDTO = new LoanNotificationDTO(
                loan.getClient().getName(),
                loan.getClient().getEmail(),
                loan.getBookTitle(),
                loan.getStatus(),
                loan.getLoanDate(),
                loan.getExpectedReturnDate()
        );

        String routingKey = "loan.status." + loan.getStatus().name().toLowerCase();

        rabbitTemplate.convertAndSend(RabbitMQConfig.LOAN_EXCHANGE_NAME, routingKey, notificationDTO);

        // Dispara a verificação da fila de reserva
        applicationContext.getBean(LoanCrudService.class).processarFilaDeReserva(book);
    }

    @Transactional
    public void processarFilaDeReserva(Book book) {
        if (book == null) {
            return;
        }
        // Busca a reserva mais antiga para este livro
        List<Loan> reservas = repository.findByBookAndStatusOrderByLoanDateAsc(book, StatusLoan.RESERVADO);

        if (!reservas.isEmpty()) {
            Loan proximaReserva = reservas.get(0); // Pega o primeiro da fila
            proximaReserva.setStatus(StatusLoan.DISPONIVEL_PARA_RETIRADA);
            repository.save(proximaReserva);

            // Envia a notificação para o cliente da reserva
            LoanNotificationDTO notificationDTO = new LoanNotificationDTO(
                    proximaReserva.getClient().getName(),
                    proximaReserva.getClient().getEmail(),
                    proximaReserva.getBookTitle(),
                    proximaReserva.getStatus(),
                    proximaReserva.getLoanDate(),
                    proximaReserva.getExpectedReturnDate()
            );
            String routingKey = "loan.status." + proximaReserva.getStatus().name().toLowerCase();
            rabbitTemplate.convertAndSend(RabbitMQConfig.LOAN_EXCHANGE_NAME, routingKey, notificationDTO);
        }
    }

    @Transactional
    public void confirmPickup(int loanId) {
        Loan loan = repository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo com ID " + loanId + " não encontrado."));

        if (loan.getStatus() != StatusLoan.DISPONIVEL_PARA_RETIRADA) {
            throw new IllegalStateException("Este empréstimo não está disponível para retirada.");
        }

        Book book = loan.getBook();
        if (book == null) {
            throw new EntityNotFoundException("Livro associado ao empréstimo não encontrado.");
        }


        // Atualiza o status do empréstimo e as datas
        loan.setStatus(StatusLoan.EMPRESTADO);
        loan.setLoanDate(LocalDateTime.now()); // A data do empréstimo começa no dia da retirada

        bookRepository.save(book);
        repository.save(loan);

        // Envia a notificação de empréstimo confirmado
        LoanNotificationDTO notificationDTO = new LoanNotificationDTO(
                loan.getClient().getName(),
                loan.getClient().getEmail(),
                loan.getBookTitle(),
                loan.getStatus(),
                loan.getLoanDate(),
                loan.getExpectedReturnDate()
        );

        String routingKey = "loan.status." + loan.getStatus().name().toLowerCase();
        rabbitTemplate.convertAndSend(RabbitMQConfig.LOAN_EXCHANGE_NAME, routingKey, notificationDTO);
    }
}
