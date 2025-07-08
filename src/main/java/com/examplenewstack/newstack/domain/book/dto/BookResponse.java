package com.examplenewstack.newstack.domain.book.dto;

public record BookResponse(
        int id,
        String title,
        String ISBN,
        String category,
        String yearPublication,

        //boolean disponibility,

        int totalQuantity,

        String author,
        String description,
        String publisher,
        String thumbnailUrl
) {
}

