package com.examplenewstack.newstack.book.exception;

public class NoBooksFoundByISBNException extends RuntimeException {

    public NoBooksFoundByISBNException(){super();}
    public NoBooksFoundByISBNException(String message) {
        super(message);
    }
}
