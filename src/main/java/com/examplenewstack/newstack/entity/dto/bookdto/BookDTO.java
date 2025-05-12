package com.examplenewstack.newstack.model.dto.bookdto;

import com.examplenewstack.newstack.model.librarie.book.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookDTO {
    @NotBlank(message = "O titulo não pode ser vazio!")
    @Size(min = 3, max = 40)
    private String title;

    @NotBlank(message = "A categoria não pode ser vazia!")
    private String category;

    @NotBlank(message = "O ano não pode ser vazio!")
    private String year_publication;

    //Sera feita a incremetação
    @NotNull(message = "A disponibilizade não pode ser vazia!")
    private boolean disponibility;

    @NotBlank(message = "A a quantidade de livros não pode ser vazia!")
    private int total_quantity;

    @NotBlank(message = "A quantidade de livros disponivel não pode ser vazia!")
    private int disponibility_quantity;


    public BookDTO() {
    }


    public BookDTO(String title, String category, String year_publication, boolean disponibility, int total_quantity, int disponibility_quantity) {
        this.title = title;
        this.category = category;
        this.year_publication = year_publication;
        this.disponibility = disponibility;
        this.total_quantity = total_quantity;
        this.disponibility_quantity = disponibility_quantity;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYear_publication() {
        return year_publication;
    }

    public void setYear_publication(String year_publication) {
        this.year_publication = year_publication;
    }

    public boolean isDisponibility() {
        return disponibility;
    }

    public void setDisponibility(boolean disponibility) {
        this.disponibility = disponibility;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getDisponibility_quantity() {
        return disponibility_quantity;
    }

    public void setDisponibility_quantity(int disponibility_quantity) {
        this.disponibility_quantity = disponibility_quantity;
    }


    public Book toBook(){
        Book book = new Book();
        book.setTitle(this.getTitle());
        book.setCategory(getCategory());
        book.setYear_publication(getYear_publication());
        book.setDisponibility(isDisponibility());
        book.setTotal_quantity(getTotal_quantity());
        book.setDisponibility_quantity(getDisponibility_quantity());

        return book;
    }
}
