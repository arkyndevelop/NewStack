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
import com.examplenewstack.newstack.domain.loan.exception.NoLoanFoundByIdException;
import com.examplenewstack.newstack.domain.loan.exception.NoLoanFoundException;
import com.examplenewstack.newstack.domain.loan.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanCrudService {

    private final LoanRepository repository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;
    private final RabbitTemplate rabbitTemplate;

    public LoanCrudService(LoanRepository repository, ClientRepository clientRepository, BookRepository bookRepository, RabbitTemplate rabbitTemplate) {
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

        StatusLoan finalStatus;

        if (bookFound.getDisponibility_quantity() <= 0) {
            // Lança uma exceção clara se não houver livros para emprestar.
            throw new Exception("Não há exemplares deste livro disponíveis para empréstimo.");
        }

        List<Loan> activeLoans = repository.findActiveLoansByClientId(loanRequest.getClientId());
        if (activeLoans.size() >= MAX_ACTIVE_LOANS_PER_CLIENT) {
            throw new Exception("Cliente atingiu o limite máximo de " + MAX_ACTIVE_LOANS_PER_CLIENT + " empréstimos ativos!");
        }

        int countSameBook = Math.toIntExact(activeLoans.stream()
                .filter(loan -> Objects.equals(loan.getBook().getId(), loanRequest.getBookId()))
                .count());

        if (countSameBook >= MAX_COPIES_OF_SAME_BOOK_PER_CLIENT) {
            throw new Exception("Este cliente já possui um empréstimo ativo para este Livro!");
        }

        finalStatus = StatusLoan.EMPRESTADO;
        // Decrementa a quantidade disponível e salva a entidade livro
        bookFound.setDisponibility_quantity(bookFound.getDisponibility_quantity() - 1);
        bookRepository.save(bookFound);

        // --- LÓGICA DA DATA DE DEVOLUÇÃO ---
        LocalDate loanDate = LocalDate.now(); // Data do empréstimo é sempre a data atual
        LocalDate expectedReturnDate = loanDate.plusDays(loanRequest.getLoanTermDays()); // Calcula a data de devolução

        // Cria a entidade Loan com as datas calculadas
        Loan loanToSave = new Loan();
        loanToSave.setClient(clientFound);
        loanToSave.setBook(bookFound);
        loanToSave.setStatus(finalStatus);
        loanToSave.setLoanDate(loanDate.atStartOfDay());
        loanToSave.setExpectedReturnDate(expectedReturnDate.atStartOfDay());

        Loan newLoan = repository.save(loanToSave);

        // lógica de notificação
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
                .map(LoanResponse::fromEntity) // Usa o novo metodo de fábrica
                .collect(Collectors.toList());
    }

//    public LoanResponse update(LoanRequest request, int id) {
//        Optional<Loan> loanFound = repository.findById(id);
//
//        loanFound.setLoanDate();
//        loanFound.get().setExpectedReturnDate(request.expectedReturnDate());
//
//        return new LoanResponse(
//                loanFound.get().getId(),
//                loanFound.get().getLoanDate(),
//                loanFound.get().getExpectedReturnDate(),
//                loanFound.get().getActualReturnDate(),
//                loanFound.get().getStatus().name(),
//                loanFound.get().getClient().getId(),
//                loanFound.get().getBook().getId()
//        );
//    }

    @Transactional
    public void delete(int loanId) {
        // 1. Busca o empréstimo no banco ou lança um erro se não existir.
        Loan loanToDelete = repository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo com ID " + loanId + " não encontrado."));

        // 2. Pega o livro associado a este empréstimo.
        Book book = loanToDelete.getBook();

        // 3. Incrementa a quantidade de exemplares disponíveis do livro.
        // Esta é a lógica inversa da criação do empréstimo.
        book.setDisponibility_quantity(book.getDisponibility_quantity() + 1);
        bookRepository.save(book); // Salva a atualização do livro

        // 4. Exclui o registro do empréstimo.
        // Como o metodo é @Transactional, se esta linha falhar, a alteração no livro será revertida.
        repository.delete(loanToDelete);
    }
}