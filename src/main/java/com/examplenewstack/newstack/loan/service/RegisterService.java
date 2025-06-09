package com.examplenewstack.newstack.loan.service;



import com.examplenewstack.newstack.loan.Loan;
import com.examplenewstack.newstack.loan.dto.LoanRequest;
import com.examplenewstack.newstack.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final LoanRepository repository;

    public RegisterService(LoanRepository repository) {
        this.repository = repository;
    }

    public Loan register(
            LoanRequest loanRequest
    ){
        return repository.save(loanRequest.toLoan());
    }
}
