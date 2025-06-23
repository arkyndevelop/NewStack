package com.examplenewstack.newstack.domain.loan.dto;

import com.examplenewstack.newstack.domain.loan.Loan;

import java.time.LocalDateTime;

public record LoanResponse(
        Integer loanID,
        LocalDateTime loanDate,
        LocalDateTime expectedReturnDate,
        LocalDateTime actualReturnDate,
        String statusLoan,

        Integer employeeId,
        Integer clientId,
        Integer bookId
){
    public static LoanResponse fromEntity(Loan loan){
        return new LoanResponse(
                loan.getId(),
                loan.getLoanDate(),
                loan.getExpectedReturnDate(),
                loan.getActualReturnDate(),
                loan.getStatus().name(),
                loan.getEmployee().getId(),
                loan.getClient().getId(),
                loan.getBook().getId()
        );
    }

}
