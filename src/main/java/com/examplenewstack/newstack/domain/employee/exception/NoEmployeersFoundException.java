package com.examplenewstack.newstack.domain.employee.exception;

public class NoEmployeersFoundException  extends RuntimeException{

    public NoEmployeersFoundException(){super("Erro: Nenhum empregado encontrado!");}

    public NoEmployeersFoundException(String message){super(message);}


}
