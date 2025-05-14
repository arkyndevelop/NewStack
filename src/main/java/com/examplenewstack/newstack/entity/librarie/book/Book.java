package com.examplenewstack.newstack.entity.librarie.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "book")

//Getters and Setters, Constructor and NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Instant dateRegister = Instant.now();

}
