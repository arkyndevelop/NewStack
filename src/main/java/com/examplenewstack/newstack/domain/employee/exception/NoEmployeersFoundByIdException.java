package com.examplenewstack.newstack.domain.employee.exception;

public class NoEmployeersFoundByIdException extends RuntimeException{
    public NoEmployeersFoundByIdException(){super("Erro: Nenhum empregado encontrado pelo id!");}
    public NoEmployeersFoundByIdException(String message){super(message);}
}


