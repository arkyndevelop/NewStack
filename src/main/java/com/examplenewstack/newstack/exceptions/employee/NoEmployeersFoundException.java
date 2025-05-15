package com.examplenewstack.newstack.exceptions.employee;

public class NoEmployeersFoundException  extends RuntimeException{

    public NoEmployeersFoundException(){super("Erro: Nenhum empregado encontrado!");}

    public NoEmployeersFoundException(String message){super(message);}


}
