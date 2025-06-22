package com.examplenewstack.newstack.book.dto;

public record BookResponseDTO (
    String title,
    String ISBN,
    String category,
    String yearPublication,

    boolean disponibility,

    int totalQuantity,
    int disponibilityQuantity,

    int collectionId,
    int employeeId

    // Setando os novos atributos na entidade Book
    String author,
    String description,
    String publisher,
    String thumbnailUrl
){
}

