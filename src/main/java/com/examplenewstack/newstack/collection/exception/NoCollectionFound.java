package com.examplenewstack.newstack.collection.exception;

public class NoCollectionFound extends RuntimeException {

    public NoCollectionFound() {super("Nenhuma coleção encontrada!");}
    public NoCollectionFound(String message) {
        super(message);
    }
}
