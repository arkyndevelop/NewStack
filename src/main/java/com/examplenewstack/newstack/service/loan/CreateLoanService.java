package com.examplenewstack.newstack.service.loan;


import com.examplenewstack.newstack.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateLoanService {

    private final LoanRepository loanRepository;


    public CreateLoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }





}
