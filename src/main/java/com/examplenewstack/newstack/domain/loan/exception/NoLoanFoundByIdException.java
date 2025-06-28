package com.examplenewstack.newstack.domain.loan.exception;

public class NoLoanFoundByIdException extends RuntimeException{

    public NoLoanFoundByIdException(){super("Error: Nenhum emprestimo feito por id");}
    public NoLoanFoundByIdException(String message){super(message);}


}
