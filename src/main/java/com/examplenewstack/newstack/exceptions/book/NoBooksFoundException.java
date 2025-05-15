package com.examplenewstack.newstack.exceptions.book;

public class NoBooksFoundException extends RuntimeException {

    public NoBooksFoundException(){ super("Nenhum livro foi encontrado!");}

    public NoBooksFoundException(String message) {
        super(message);
    }

}

