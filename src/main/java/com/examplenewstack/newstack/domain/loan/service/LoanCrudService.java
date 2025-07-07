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
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Loan register(LoanRequest loanRequest, int clientId, int bookId) {
        Client clientFound = clientRepository.findById(clientId)
                .orElseThrow();
        Book bookFound = bookRepository.findById(bookId)
                .orElseThrow();

        StatusLoan finalStatus;


        if (bookFound.getDisponibility_quantity() > 0) {
            finalStatus = StatusLoan.EMPRESTADO;

            bookFound.setDisponibility_quantity(bookFound.getDisponibility_quantity() - 1);

            bookRepository.save(bookFound);


        } else {
            finalStatus = StatusLoan.RESERVADO;
        }

        Loan newLoan = repository.save(loanRequest.toLoan(clientFound, bookFound, finalStatus));

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

    public List<LoanResponse> reportAll() {
        List<Loan> loanFound = repository.findAll();
        if (loanFound.isEmpty()) {
            throw new NoLoanFoundException();
        }

        return loanFound.stream()
                .map(loan -> new LoanResponse(
                        loan.getId(),
                        loan.getLoanDate(),
                        loan.getExpectedReturnDate(),
                        loan.getActualReturnDate(),
                        loan.getStatus().name(),
                        loan.getClient().getId(),
                        loan.getBook().getId()))
                .toList();
    }

    public LoanResponse reportById(int id) {
        Optional<Loan> loanFound = repository.findById(id);
        if (!loanFound.isPresent()) {
            throw new NoLoanFoundByIdException();
        }

        return new LoanResponse(
                loanFound.get().getId(),
                loanFound.get().getLoanDate(),
                loanFound.get().getExpectedReturnDate(),
                loanFound.get().getActualReturnDate(),
                loanFound.get().getStatus().name(),
                loanFound.get().getClient().getId(),
                loanFound.get().getBook().getId()
        );
    }

    public LoanResponse update(LoanRequest request, int id) {
        Optional<Loan> loanFound = repository.findById(id);
        if (!loanFound.isPresent()) {
            throw new NoLoanFoundByIdException();
        }

        loanFound.get().setLoanDate(request.loanDate());
        loanFound.get().setExpectedReturnDate(request.expectedReturnDate());

        return new LoanResponse(
                loanFound.get().getId(),
                loanFound.get().getLoanDate(),
                loanFound.get().getExpectedReturnDate(),
                loanFound.get().getActualReturnDate(),
                loanFound.get().getStatus().name(),
                loanFound.get().getClient().getId(),
                loanFound.get().getBook().getId()
        );
    }

    public List<LoanResponse> deleteAll() {
        List<Loan> loanList = repository.findAll();
        if (loanList.isEmpty()) {
            throw new NoLoanFoundException();
        }

        for (Loan loan : loanList) {
            if (loan.getStatus() == StatusLoan.EMPRESTADO) {
                Book books = loan.getBook();

                books.setDisponibility_quantity(books.getDisponibility_quantity() + 1);

                bookRepository.save(books);

            }
        }

        repository.deleteAll();

        return loanList.stream()
                .map(loan -> new LoanResponse(
                        loan.getId(),
                        loan.getLoanDate(),
                        loan.getExpectedReturnDate(),
                        loan.getActualReturnDate(),
                        loan.getStatus().name(),
                        loan.getClient().getId(),
                        loan.getBook().getId()))
                .toList();
    }

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
                        loan.getClient().getId(),
                        loan.getBook().getId()))
                .toList();


    }
}
