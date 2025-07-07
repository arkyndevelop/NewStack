package com.examplenewstack.newstack.domain.loan.dto;

import com.examplenewstack.newstack.domain.loan.enums.StatusLoan;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public record LoanNotificationDTO(
        String clientName,
        String clientEmail,
        String bookTitle,
        StatusLoan statusLoan,
        LocalDateTime loanDate,
        LocalDateTime expectedReturnDate
        ) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
