package com.examplenewstack.newstack.entity.book;

import com.examplenewstack.newstack.entity.collection.Collection;
import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.entity.loan.Loan;
import com.examplenewstack.newstack.entity.user.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.Instant;
import java.time.LocalDateTime;
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
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 17)
    private String ISBN;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false)
    private String year_publication;

    // Apenas um campo booleano para indicar se está disponível
    @Column(nullable = false)
    private Boolean availability;

    // Quantidade total de exemplares
    @Column(nullable = false)
    private int total_quantity;

    // Quantidade disponível para empréstimo
    @Column(nullable = false)
    private int disponibility_quantity;

    @Column(nullable = false)  //Falha em usar @Temporal com Instant
    private Instant dateRegister = Instant.now();

    // Relacionamentos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Loan> loans;

}
