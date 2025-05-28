package com.examplenewstack.newstack.book.exception;

public class NoBooksFoundException extends RuntimeException {

    public NoBooksFoundException(){ super("Nenhum livro foi encontrado!");}

    public NoBooksFoundException(String message) {
        super(message);
    }

}

