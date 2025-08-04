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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoanCrudService {

    private final LoanRepository repository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;
    private final RabbitTemplate rabbitTemplate;

    public LoanCrudService(LoanRepository repository, ClientRepository clientRepository,
                           BookRepository bookRepository, RabbitTemplate rabbitTemplate) {
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

        if (bookFound.getDisponibility_quantity() <= 0) {
            throw new Exception("Não há exemplares deste livro disponíveis para empréstimo.");
        }

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

        // Decrementa a quantidade disponível
        bookFound.setDisponibility_quantity(bookFound.getDisponibility_quantity() - 1);
        bookRepository.save(bookFound);

        LocalDate loanDate = LocalDate.now();
        LocalDate expectedReturnDate = loanDate.plusDays(loanRequest.getLoanTermDays());

        Loan loanToSave = new Loan();
        loanToSave.setClient(clientFound);
        loanToSave.setBook(bookFound);
        loanToSave.setStatus(StatusLoan.EMPRESTADO);
        loanToSave.setLoanDate(loanDate.atStartOfDay());
        loanToSave.setExpectedReturnDate(expectedReturnDate.atStartOfDay());

        //ao registrar, salvamos também o título fixo na entidade
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
    }
}
