package com.examplenewstack.newstack.employee.exception;

public class NoEmployeersFoundByIdException extends RuntimeException{
    public NoEmployeersFoundByIdException(){super("Erro: Nenhum empregado encontrado pelo id!");}
    public NoEmployeersFoundByIdException(String message){super(message);}
}


