package com.examplenewstack.newstack.exceptions.client;

public class ClientsRegisteredDataException extends  RuntimeException{

    public ClientsRegisteredDataException(){super("Falha ao cadastrar!");}

    public ClientsRegisteredDataException(String message){super(message);}

}
