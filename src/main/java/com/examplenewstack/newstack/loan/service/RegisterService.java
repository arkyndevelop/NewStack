package com.examplenewstack.newstack.loan.service;

import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.repository.BookRepository;
import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.client.repository.ClientRepository;
import com.examplenewstack.newstack.loan.Loan;
import com.examplenewstack.newstack.loan.dto.LoanRequest;
import com.examplenewstack.newstack.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final LoanRepository repository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;

    public RegisterService(LoanRepository repository, ClientRepository clientRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
    }

    public Loan register(
            LoanRequest loanRequest,
            int clientId,
            int bookId
    ){
        Client clientFound = clientRepository.findById(clientId)
                .orElseThrow();
        Book bookFound = bookRepository.findById(bookId)
                .orElseThrow();
        return repository.save(loanRequest.toLoan(clientFound,bookFound));
    }
}
