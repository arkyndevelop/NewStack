package com.examplenewstack.newstack.exception.ClientsException;

public class NoCustomersFoundException extends RuntimeException{

    public NoCustomersFoundException(){super("Nenhum Cliente encontrado!");}

    public NoCustomersFoundException(String message){super(message);}

}
