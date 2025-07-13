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

    @Transactional
    public Loan register(LoanRequest loanRequest) throws Exception {
        Client clientFound = clientRepository.findById(loanRequest.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
        Book bookFound = bookRepository.findById(loanRequest.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        StatusLoan finalStatus;

        if (bookFound.getDisponibility_quantity() > 0) {
            finalStatus = StatusLoan.EMPRESTADO;
            // Decrementa a quantidade disponível e salva a entidade livro
            bookFound.setDisponibility_quantity(bookFound.getDisponibility_quantity() - 1);
            bookRepository.save(bookFound);
        } else {
            // Lança uma exceção clara se não houver livros para emprestar.
            throw new Exception("Não há exemplares deste livro disponíveis para empréstimo.");
        }

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

        // O restante da lógica de notificação permanece o mesmo
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
                .map(LoanResponse::fromEntity) // Usa o novo método de fábrica
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

    public List<LoanResponse> deleteById(int id) {

        Optional<Loan> loanList = repository.findById(id);

        if (loanList.isEmpty()) {
            throw new NoLoanFoundByIdException();
        }

        if(loanList.get().getStatus() == StatusLoan.EMPRESTADO){


            Book books = loanList.get().getBook();

            books.setDisponibility_quantity(books.getDisponibility_quantity() + 1);

            bookRepository.save(books);

        }

        repository.deleteById(id);

        return loanList.stream()
                .map(loan -> new LoanResponse(
                        loan.getId(),
                        loan.getLoanDate(),
                        loan.getExpectedReturnDate(),
                        loan.getActualReturnDate(),
                        loan.getStatus().name(),
                        loan.getClient().getName(),
                        loan.getBook().getTitle()))
                .toList();
    }
}