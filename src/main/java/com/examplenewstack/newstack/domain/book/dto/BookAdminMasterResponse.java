package com.examplenewstack.newstack.domain.book.dto;

public record BookAdminMasterResponse(
        Integer id,
        String title,
        String ISBN,
        String category,
        String yearPublication,

        boolean disponibility,

        int totalQuantity,
        int disponibilityQuantity,

        Integer collectionId,
        Integer employeeId,

        String author,
        String description,
        String publisher,
        String thumbnailUrl
) {
}

