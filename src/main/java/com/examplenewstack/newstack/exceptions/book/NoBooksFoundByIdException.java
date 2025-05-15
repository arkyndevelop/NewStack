package com.examplenewstack.newstack.exceptions.book;

import com.examplenewstack.newstack.exceptions.employee.NoEmployeersFoundByIdException;

public class NoBooksFoundByIdException extends RuntimeException {
    public NoBooksFoundByIdException(){super("Livro não encontrado!");}
    public NoBooksFoundByIdException(String message) {
        super(message);
    }
}
