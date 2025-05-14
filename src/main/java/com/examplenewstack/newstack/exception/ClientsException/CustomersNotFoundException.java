package com.examplenewstack.newstack.exception.ClientsException;

public class CustomersNotFoundException extends RuntimeException{

    public CustomersNotFoundException(){super("Clientes não encontrados!");}

    public CustomersNotFoundException(String message){super(message);}


}
