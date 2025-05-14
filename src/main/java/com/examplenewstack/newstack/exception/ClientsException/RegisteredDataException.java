package com.examplenewstack.newstack.exception.ClientsException;

public class RegisteredDataException  extends  RuntimeException{

    public RegisteredDataException(){super("Dados ja cadastrados!");}

    public RegisteredDataException(String message){super(message);}

}
