package com.examplenewstack.newstack.domain.loan.enums;

public enum StatusLoan {
    RESERVADO("RESERVADO"),
    EMPRESTADO("EMPRESTADO"),
    RENOVADO("RENOVADO"),
    DEVOLVIDO("DEVOLVIDO"),
    ATRASADO("ATRASADO"),
    PERDIDO("PERDIDO"),
    CANCELADO("CANCELADO");

    private final String status;

    StatusLoan(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
