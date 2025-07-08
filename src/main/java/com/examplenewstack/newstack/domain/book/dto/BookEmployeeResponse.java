package com.examplenewstack.newstack.domain.book.dto;

public record BookEmployeeResponse(
        Integer id,
        String title,
        String ISBN,
        String category,
        String yearPublication,
        boolean disponibility,
        int totalQuantity,
        int disponibilityQuantity,
        int collectionId,
        String author,
        String description,
        String publisher,
        String thumbnailUrl
) {
}

