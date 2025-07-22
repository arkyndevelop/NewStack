package com.examplenewstack.newstack.domain.book.dto;

import com.examplenewstack.newstack.domain.book.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.ISBN;

public record BookUpdateRequest(
        @NotBlank(message = "O titulo não pode ser vazio!")
        @Size(min = 3, max = 40)
        String title,

        @ISBN
        @NotBlank(message = "O ISBN não pode estar vazio!")
        String ISBN,

        @NotBlank(message = "A categoria não pode ser vazia!")
        String category,

        @NotNull(message = "O ano não pode ser vazio!")
        Integer year_publication,

        @NotNull(message = "A quantidade de livros não pode ser vazia!")
        int total_quantity,

        String author,
        String description,
        String publisher


) {

        public Book tobook() {
                Book book = new Book();

                book.setTitle(title);
                book.setISBN(ISBN);
                book.setCategory(category);
                book.setYear_publication(year_publication);
                book.setTotal_quantity(total_quantity);
                book.setAuthor(author);
                book.setDescription(description);
                book.setPublisher(publisher);


                return book;
        }

}
