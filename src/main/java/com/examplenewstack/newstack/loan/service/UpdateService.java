package com.examplenewstack.newstack.loan.service;

import com.examplenewstack.newstack.loan.Loan;
import com.examplenewstack.newstack.loan.dto.LoanRequest;
import com.examplenewstack.newstack.loan.dto.LoanResponse;
import com.examplenewstack.newstack.loan.exception.NoLoanFoundByIdException;
import com.examplenewstack.newstack.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateService {

    private final LoanRepository repository;

    public UpdateService(LoanRepository repository) {
        this.repository = repository;
    }

    public LoanResponse update(
            LoanRequest request,
            int id
    ){
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
                loanFound.get().getEmployee().getId(),
                loanFound.get().getBook().getId()
        );
    }
}
