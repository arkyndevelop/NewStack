package com.examplenewstack.newstack.collection.exception;

public class NoCollectionFoundException extends RuntimeException {

    public NoCollectionFoundException() {super("Nenhuma coleção encontrada!");}
    public NoCollectionFoundException(String message) {
        super(message);
    }
}
