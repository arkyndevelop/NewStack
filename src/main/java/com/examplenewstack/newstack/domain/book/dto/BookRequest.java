package com.examplenewstack.newstack.domain.book.dto;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.collection.Collection;
import com.examplenewstack.newstack.domain.employee.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.ISBN;

public record BookRequest(
        @NotBlank(message = "O titulo não pode ser vazio!")
        @Size(min = 3, max = 40)
        String title,

        //@ISBN
        //@NotBlank(message = "O ISBN não pode estar vazio!")
        @Size(min = 13, max = 17)
        String ISBN,

        @NotBlank(message = "A categoria não pode ser vazia!")
        String category,

        @NotNull(message = "O ano não pode ser vazio!")
        String year_publication,

        @NotNull(message = "A disponibilidade não pode ser vazia!")
        boolean disponibility,

        @NotNull(message = "A quantidade de livros não pode ser vazia!")
        int total_quantity,

        @NotNull(message = "A quantidade de livros disponível não pode ser vazia!")
        int disponibility_quantity,

        int collectionId,
        int employeeId,

        String author,
        String description,
        String publisher,
        String thumbnailUrl

) {
    public Book tobook(Collection collection, Employee employee) {
        Book book = new Book();

        book.setTitle(title);
        book.setISBN(ISBN);
        book.setCategory(category);
        book.setYear_publication(year_publication);
        book.setDisponibility(disponibility);
        book.setTotal_quantity(total_quantity);
        book.setDisponibility_quantity(disponibility_quantity);

        book.setEmployee(employee);
        book.setCollection(collection);

        // Novos campos
        book.setAuthor(author);
        book.setDescription(description);
        book.setPublisher(publisher);
        book.setThumbnailUrl(thumbnailUrl);

        return book;
    }
}
