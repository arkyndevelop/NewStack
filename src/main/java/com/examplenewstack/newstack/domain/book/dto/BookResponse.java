package com.examplenewstack.newstack.domain.book.dto;

public record BookResponse(
        int id,
        String title,
        String ISBN,
        String category,
        String yearPublication,

        boolean disponibility,

        int totalQuantity,
        int disponibilityQuantity,

        int collectionId,
        int employeeId,

        String author,
        String description,
        String publisher,
        String thumbnailUrl
) {
}

