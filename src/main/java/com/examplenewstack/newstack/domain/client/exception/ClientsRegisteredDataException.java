package com.examplenewstack.newstack.domain.client.exception;

public class ClientsRegisteredDataException extends  RuntimeException{

    public ClientsRegisteredDataException(){super("Falha ao cadastrar!");}

    public ClientsRegisteredDataException(String message){super(message);}

}
