package com.examplenewstack.newstack.loan.service;


import com.examplenewstack.newstack.loan.Loan;
import com.examplenewstack.newstack.loan.dto.LoanRequestDTO;
import com.examplenewstack.newstack.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateLoanService {

    private final LoanRepository repository;

    public CreateLoanService(LoanRepository repository) {
        this.repository = repository;
    }

//    public LoanRequestDTO create(){
//
//
//    }
}
