package com.examplenewstack.newstack.domain.book.dto;

public record BookClientResponse(
        Integer id,
        String title,
        String ISBN,
        String category,
        String yearPublication,
        boolean disponibility,
        int disponibilityQuantity,
        String author,
        String description,
        String publisher,
        String thumbnailUrl
) {
}

