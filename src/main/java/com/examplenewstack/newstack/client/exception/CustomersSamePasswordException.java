package com.examplenewstack.newstack.client.exception;

public class CustomersSamePasswordException extends RuntimeException{

    public CustomersSamePasswordException(){super("Erro: senhas não são iguais!");}
    public CustomersSamePasswordException(String message){super(message);}
}
