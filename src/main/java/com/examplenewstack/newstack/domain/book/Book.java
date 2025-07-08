package com.examplenewstack.newstack.domain.book;

import com.examplenewstack.newstack.domain.collection.Collection;
import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.loan.Loan;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.Instant;
import java.util.List;


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
    private int id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 1000)
    private String author;

    @Column(length = 17)
    private String ISBN;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false, length = 255)
    private String publisher;

    @Column(nullable = false, length = 1000)
    private String thumbnailUrl;

    @Column(nullable = false)
    private String year_publication;

    // Apenas um campo boolean para indicar se está disponível
    @Column(nullable = false)
    private boolean disponibility;

    // Quantidade total de exemplares
    @Column(nullable = false)
    private Integer total_quantity;

    // Quantidade disponível para empréstimo
    @Column(nullable = false)
    private int disponibility_quantity;

    @Column(nullable = false)  //Falha em usar @Temporal com Instant
    private Instant dateRegister = Instant.now();

    // Relacionamentos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = true)
    private Collection collection;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @JsonManagedReference("book-loan")
    private List<Loan> loans;
}
