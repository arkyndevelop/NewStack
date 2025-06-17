package com.examplenewstack.newstack.loan.dto;

import com.examplenewstack.newstack.loan.Loan;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record LoanRequest(
        @NotNull
        LocalDateTime loanDate,

        @NotNull(message = "Data de devolução obrigatória!")
        @Future(message = "Data de devolução deve ser futura!")
        LocalDateTime expectedReturnDate,

        @NotNull
        int clientId,

        @NotNull
        int bookId
) {
        public Loan toLoan(){
                Loan loan = new Loan();
                loan.setLoanDate(loanDate);
                loan.setExpectedReturnDate(expectedReturnDate);
                return loan;
        }

}
