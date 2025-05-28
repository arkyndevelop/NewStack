package com.examplenewstack.newstack.loan.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record LoanRequestDTO(
        @NotNull
        LocalDateTime loanDate,

        @NotNull(message = "Data de devolução obrigatória!")
        @Future(message = "Data de devolução deve ser futura!")
        LocalDateTime expectedReturnDate,

        @NotNull
        Long clientId,

        @NotNull
        Long bookId
) {

}
