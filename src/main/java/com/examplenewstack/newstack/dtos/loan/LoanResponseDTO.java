package com.examplenewstack.newstack.dtos.loan;

import com.examplenewstack.newstack.entity.loan.Loan;

import java.time.LocalDateTime;

public record LoanResponseDTO (
        Long loanID,
        LocalDateTime loanDate,
        LocalDateTime expectedReturnDate,
        LocalDateTime actualReturnDate,
        String statusLoan,

        Long employeeId,
        Long clientId,
        Long bookId
){
    public static LoanResponseDTO fromEntity(Loan loan){
        return new LoanResponseDTO(
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
