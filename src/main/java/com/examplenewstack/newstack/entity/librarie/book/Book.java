package com.examplenewstack.newstack.entity.librarie.book;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,length = 255)
    private String title;

    @Column(nullable = false,length = 100)
    private String category;

    @Column(nullable = false)
    private String year_publication;

    @Column(nullable = false)
    private Boolean disponibility;

    @Column(nullable = false)
    private int total_quantity;

    @Column(nullable = false)
    private int disponibility_quantity;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date_register;

    @Deprecated
    public Book() {}

    public Book(String title, String category, String year_publication, Boolean disponibility, int total_quantity, int disponibility_quantity, LocalDateTime date_register) {
        this.title = title;
        this.category = category;
        this.year_publication = year_publication;
        this.disponibility = disponibility;
        this.total_quantity = total_quantity;
        this.disponibility_quantity = disponibility_quantity;
        this.date_register = date_register;
    }

    // Getters e Setters

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

    public Boolean getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(Boolean disponibility) {
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

    public LocalDateTime getDate_register() {
        return date_register;
    }
}
