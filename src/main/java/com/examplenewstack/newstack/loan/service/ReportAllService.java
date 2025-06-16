package com.examplenewstack.newstack.loan.service;

import com.examplenewstack.newstack.book.repository.BookRepository;
import com.examplenewstack.newstack.client.repository.ClientRepository;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import com.examplenewstack.newstack.loan.Loan;
import com.examplenewstack.newstack.loan.dto.LoanResponse;
import com.examplenewstack.newstack.loan.exception.NoLoanFoundException;
import com.examplenewstack.newstack.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportAllService {

    private final LoanRepository repository;

    public ReportAllService(LoanRepository repository) {
        this.repository = repository;
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
                        loan.getEmployee().getId(),
                        loan.getClient().getId(),
                        loan.getBook().getId()))
                .toList();
    }
}
