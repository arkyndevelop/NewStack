package com.examplenewstack.newstack.domain.loan.enums;




public enum StatusLoan {
    RESERVADO("RESERVADO"),
    EMPRESTADO("EMPRESTADO"),
    DEVOLVIDO("DEVOLVIDO"),
    ATRASADO("ATRASADO"),
    PERDIDO("PERDIDO"),
    CANCELADO("CANCELADO"),
    DISPONIVEL_PARA_RETIRADA("DISPONÍVEL PARA RETIRADA");

    private final String status;

    StatusLoan(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
