package com.examplenewstack.newstack.loan.dto;

import com.examplenewstack.newstack.loan.Loan;

import java.time.LocalDateTime;

public record LoanResponse(
        Integer loanID,
        LocalDateTime loanDate,
        LocalDateTime expectedReturnDate,
        LocalDateTime actualReturnDate,
        String statusLoan,

        Integer clientId,
        Integer bookId
){
    public static LoanResponse fromEntity(Loan loan){
        return new LoanResponse(
                loan.getId(),
                loan.getLoanDate(),
                loan.getExpectedReturnDate(),
                loan.getActualReturnDate(),
                loan.getStatus().getStatus(),
                loan.getClient().getId(),
                loan.getBook().getId()
        );
    }

}
