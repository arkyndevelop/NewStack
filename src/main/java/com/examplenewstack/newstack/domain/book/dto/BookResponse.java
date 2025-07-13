package com.examplenewstack.newstack.domain.book.dto;

import com.examplenewstack.newstack.domain.book.Book;

/**
 * DTO completo para a entidade Livro, servindo a todas as telas.
 * Usa a convenção camelCase para compatibilidade com o Thymeleaf.
 */
public record BookResponse(
        int id,
        String title,
        String author,
        int publicationYear,
        String publisher,
        int totalQuantity,
        int disponibilityQuantity,
        String thumbnailUrl
) {
    public static BookResponse fromEntity(Book book) {
        // Lógica para fornecer uma imagem padrão, caso nenhuma seja definida
        String imageUrl = (book.getThumbnailUrl() == null || book.getThumbnailUrl().isBlank())
                ? "/images/default-book-cover.png" // Caminho para uma imagem padrão
                : book.getThumbnailUrl();

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear_publication(),
                book.getPublisher(),
                book.getTotal_quantity(),
                book.getDisponibility_quantity(),
                imageUrl
        );
    }
}