package com.examplenewstack.newstack.domain.client.exception;

public class NoCustomersFoundByIdException extends RuntimeException{

    public NoCustomersFoundByIdException(){super("Erro: nenhum cliente encontrado pelo id");}

    public NoCustomersFoundByIdException(String message){super(message);}
}
