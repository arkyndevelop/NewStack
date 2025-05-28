package com.examplenewstack.newstack.book.exception;

public class NoBooksFoundByIdException extends RuntimeException {
    public NoBooksFoundByIdException(){super("Livro não encontrado!");}
    public NoBooksFoundByIdException(String message) {
        super(message);
    }
}
