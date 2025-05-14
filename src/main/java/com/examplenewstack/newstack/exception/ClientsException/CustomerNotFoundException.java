package com.examplenewstack.newstack.exception.ClientsException;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(){super("Clientes não encontrados!");}

    public CustomerNotFoundException(String message){super(message);}


}
