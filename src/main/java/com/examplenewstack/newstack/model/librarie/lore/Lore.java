package com.examplenewstack.newstack.model.librarie.lore;

import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    private int isbm;

    @Column(nullable = false)
    private int year_publication;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Boolean availability;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime register;

    @Deprecated
    public Lore() {}

    public Lore(String title, int isbm, int year_publication, String category) {
        this.title = title;
        this.isbm = isbm;
        this.year_publication = year_publication;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsbm() {
        return isbm;
    }

    public void setIsbm(int isbm) {
        this.isbm = isbm;
    }

    public int getYear_publication() {
        return year_publication;
    }

    public void setYear_publication(int year_publication) {
        this.year_publication = year_publication;
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

    public LocalDateTime getRegister() {
        return register;
    }

    public void setRegister(LocalDateTime register) {
        this.register = LocalDateTime.now();
    }
}


