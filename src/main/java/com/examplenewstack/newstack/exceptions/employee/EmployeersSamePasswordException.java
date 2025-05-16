package com.examplenewstack.newstack.exceptions.employee;

public class EmployeersSamePasswordException extends RuntimeException{

    //Chama um com super com a mensagem que deseja na exceções
    public EmployeersSamePasswordException(){super("Erro: senhas não são iguais!");}

    //criar com paramento da mensagem e chama no super pra pega a mensagem que vc deseja VALEUUUUU!!!
    public EmployeersSamePasswordException(String message){super(message);}

}
