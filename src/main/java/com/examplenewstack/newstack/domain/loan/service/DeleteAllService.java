package com.examplenewstack.newstack.domain.loan.service;


import com.examplenewstack.newstack.domain.loan.Loan;
import com.examplenewstack.newstack.domain.loan.dto.LoanResponse;
import com.examplenewstack.newstack.domain.loan.exception.NoLoanFoundException;
import com.examplenewstack.newstack.domain.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteAllService {

    private final LoanRepository loanRepository;

    public DeleteAllService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<LoanResponse> deleteAllLoan() {
        List<Loan> loanList = loanRepository.findAll();
        if (loanList.isEmpty()) {
            throw new NoLoanFoundException();
        }

        loanRepository.deleteAll();

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
