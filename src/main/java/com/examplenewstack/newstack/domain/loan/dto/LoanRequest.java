package com.examplenewstack.newstack.domain.loan.dto;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.loan.Loan;
import com.examplenewstack.newstack.domain.loan.enums.StatusLoan;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record LoanRequest(
        @NotNull
        LocalDateTime loanDate,

        @NotNull(message = "Data de devolução obrigatória!")
        @Future(message = "Data de devolução deve ser futura!")
        LocalDateTime expectedReturnDate,

        @NotNull @Enumerated(EnumType.STRING)
        StatusLoan statusLoan,

        @NotNull
        int clientId,

        @NotNull
        int bookId
) {
        public Loan toLoan(Client client, Book book){
                Loan loan = new Loan();

                loan.setLoanDate(loanDate);
                loan.setExpectedReturnDate(expectedReturnDate);
                loan.setStatus(statusLoan);
                loan.setClient(client);
                loan.setBook(book);

                return loan;
        }

}
