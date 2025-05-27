package com.examplenewstack.newstack.dtos.book;

import com.examplenewstack.newstack.entity.book.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ISBN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @NotBlank(message = "O titulo não pode ser vazio!")
    @Size(min = 3, max = 40)
    private String title;

    @ISBN
    @NotBlank(message = "O ISBN não pode estar vazio!")
    @Size(min = 13, max = 17)
    private String ISBN;

    @NotBlank(message = "A categoria não pode ser vazia!")
    private String category;

    @NotBlank(message = "O ano não pode ser vazio!")
    private String year_publication;

    //Sera feita a incremetação
    @NotNull(message = "A disponibilidade não pode ser vazia!")
    private boolean disponibility;

    @NotBlank(message = "A a quantidade de livros não pode ser vazia!")
    private int total_quantity;

    @NotBlank(message = "A quantidade de livros disponivel não pode ser vazia!")
    private int disponibility_quantity;

    public Book toBook(){
        Book book = new Book();
        book.setTitle(this.getTitle());
        book.setCategory(getCategory());
        book.setYear_publication(getYear_publication());
        book.setAvailability(isDisponibility());
        book.setTotal_quantity(getTotal_quantity());
        book.setDisponibility_quantity(getDisponibility_quantity());
        book.setISBN(getISBN());

        return book;
    }
}
