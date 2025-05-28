package com.examplenewstack.newstack.client.exception;

public class NoCustomersFoundException extends RuntimeException{

    public NoCustomersFoundException(){super("Nenhum Cliente encontrado!");}

    public NoCustomersFoundException(String message){super(message);}

}
