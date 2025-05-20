package com.examplenewstack.newstack.exceptions.book;

public class NoBooksFoundByISBNException extends RuntimeException {

    public NoBooksFoundByISBNException(){super();}
    public NoBooksFoundByISBNException(String message) {
        super(message);
    }
}
