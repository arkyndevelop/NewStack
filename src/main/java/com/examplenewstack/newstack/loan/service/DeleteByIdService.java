package com.examplenewstack.newstack.loan.service;


import com.examplenewstack.newstack.loan.Loan;
import com.examplenewstack.newstack.loan.dto.LoanResponse;
import com.examplenewstack.newstack.loan.exception.NoLoanFoundByIdException;
import com.examplenewstack.newstack.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeleteByIdService {

    private final LoanRepository loanRepository;


    public DeleteByIdService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }



    public List<LoanResponse> deleteById(int id){

        Optional<Loan> loanList = loanRepository.findById(id);

        if(loanList.isEmpty()){
            throw new NoLoanFoundByIdException();
        }

        loanRepository.deleteById(id);


        return  loanList.stream()
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
