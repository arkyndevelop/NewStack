package com.examplenewstack.newstack.entity.book;

import com.examplenewstack.newstack.entity.user.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.Instant;
import java.time.LocalDateTime;



//Getters and Setters, Constructor and NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,length = 255)
    private String title;

    @Column(nullable = false, length = 17)
    private String ISBN;

    @Column(nullable = false,length = 100)
    private String category;

    @Column(nullable = false)
    private String year_publication;

    @Column(nullable = false)
    private boolean disponibility;

    @Column(nullable = false)
    private int total_quantity;

    @Column(nullable = false)
    private int disponibility_quantity;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant dateRegister = Instant.now();

}
