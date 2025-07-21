package com.examplenewstack.newstack.domain.loan.dto;

import com.examplenewstack.newstack.domain.loan.Loan;

import java.time.LocalDateTime;

/**
 * DTO completo para exibir informações de empréstimos nos relatórios.
 */
public record LoanResponse(
        int id,
        LocalDateTime loanDate,
        LocalDateTime expectedReturnDate,
        LocalDateTime actualReturnDate,
        String status,
        String clientName,
        String bookTitle
) {
    public static LoanResponse fromEntity(Loan loan) {
        // Verifica se o livro existe antes de pegar o título
        String title = (loan.getBook() != null) ? loan.getBook().getTitle() : "Livro excluído";

        return new LoanResponse(
                loan.getId(),
                loan.getLoanDate(),
                loan.getExpectedReturnDate(),
                loan.getActualReturnDate(),
                loan.getStatus().name(),
                loan.getClient().getName(),
                title
        );
    }
}
