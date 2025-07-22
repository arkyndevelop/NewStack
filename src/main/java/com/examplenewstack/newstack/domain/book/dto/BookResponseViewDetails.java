package com.examplenewstack.newstack.domain.book.dto;

import com.examplenewstack.newstack.domain.book.Book;

public record BookResponseViewDetails(
        int id,
        String title,
        String author,
        String publisher,
        String category,
        String description,
        String ISBN,
        String thumbnailUrl,
        Integer year_publication,
        Integer total_quantity
) {
    public static BookResponseViewDetails fromEntity(Book book) {
        String imageUrl = (book.getThumbnailUrl() == null || book.getThumbnailUrl().isBlank())
                ? "/images/default-book-cover.png"
                : book.getThumbnailUrl();

        return new BookResponseViewDetails(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getCategory(),
                book.getDescription(),
                book.getISBN(),
                imageUrl,
                book.getYear_publication(),
                book.getTotal_quantity()
        );
    }
}