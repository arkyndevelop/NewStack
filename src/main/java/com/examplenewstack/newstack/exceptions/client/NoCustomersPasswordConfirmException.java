package com.examplenewstack.newstack.exceptions.client;

public class NoCustomersPasswordConfirmException extends RuntimeException{

    public NoCustomersPasswordConfirmException(){super("Erro: senhas não são iguais!");}
    public NoCustomersPasswordConfirmException(String message){super(message);}
}
