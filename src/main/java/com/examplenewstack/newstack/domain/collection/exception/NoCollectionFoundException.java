package com.examplenewstack.newstack.domain.collection.exception;

public class NoCollectionFoundException extends RuntimeException {

    public NoCollectionFoundException() {super("Nenhuma coleção encontrada!");}
    public NoCollectionFoundException(String message) {
        super(message);
    }
}
