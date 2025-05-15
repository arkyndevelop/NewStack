package com.examplenewstack.newstack.exceptions.client;

public class NoCustomersFoundByIdException extends RuntimeException{

    public NoCustomersFoundByIdException(){super("Erro: nenhum cliente encontrado pelo id");}

    public NoCustomersFoundByIdException(String message){super(message);}
}
