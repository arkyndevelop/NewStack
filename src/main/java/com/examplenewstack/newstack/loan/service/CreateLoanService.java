package com.examplenewstack.newstack.loan.service;


import com.examplenewstack.newstack.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateLoanService {

    @Autowired
    private final LoanRepository loanRepository;


    public CreateLoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }





}
