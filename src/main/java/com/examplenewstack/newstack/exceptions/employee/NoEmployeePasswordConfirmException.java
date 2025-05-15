package com.examplenewstack.newstack.exceptions.employee;

public class NoEmployeePasswordConfirmException extends RuntimeException{

    public NoEmployeePasswordConfirmException(){super("Erro: senhas não são iguais!");}

    public NoEmployeePasswordConfirmException(String message){super(message);}

}
