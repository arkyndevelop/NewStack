package com.examplenewstack.newstack.domain.loan.exception;

public class NoLoanFoundException extends RuntimeException {
    public NoLoanFoundException(){
        super("Error: Nenhum emprestimo encontrado!");
    }
    public NoLoanFoundException(String message) {
        super(message);
    }
}
