package com.examplenewstack.newstack.model.librarie.lore;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "lore")
public class Lore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private int yearPublication;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Boolean availability;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date register;

    @Deprecated
    public Lore() {}

    public Lore(String title, String isbn, int yearPublication, String category) {
        this.title = title;
        this.isbn = isbn;
        this.yearPublication = yearPublication;
        this.category = category;
        this.availability = true;
        this.register = new Date();
    }

    // Getters e Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
