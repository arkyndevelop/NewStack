package com.examplenewstack.newstack.domain.loan.service;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.repository.BookRepository;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import com.examplenewstack.newstack.domain.loan.Loan;
import com.examplenewstack.newstack.domain.loan.dto.LoanRequest;
import com.examplenewstack.newstack.domain.loan.dto.LoanResponse;
import com.examplenewstack.newstack.domain.loan.exception.NoLoanFoundByIdException;
import com.examplenewstack.newstack.domain.loan.exception.NoLoanFoundException;
import com.examplenewstack.newstack.domain.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanCrudService {

    private final LoanRepository repository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;

    public LoanCrudService(LoanRepository repository, ClientRepository clientRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
    }

    public Loan register(LoanRequest loanRequest, int clientId, int bookId){
        Client clientFound = clientRepository.findById(clientId)
                .orElseThrow();
        Book bookFound = bookRepository.findById(bookId)
                .orElseThrow();
        return repository.save(loanRequest.toLoan(clientFound, bookFound));
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

    public LoanResponse update(LoanRequest request, int id){
        Optional<Loan> loanFound = repository.findById(id);
        if (!loanFound.isPresent()){
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

    public List<LoanResponse> deleteById(int id){

        Optional<Loan> loanList = repository.findById(id);

        if(loanList.isEmpty()){
            throw new NoLoanFoundByIdException();
        }

        repository.deleteById(id);


        return  loanList.stream()
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
