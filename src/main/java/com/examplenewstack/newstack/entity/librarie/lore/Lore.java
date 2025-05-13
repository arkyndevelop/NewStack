package com.examplenewstack.newstack.entity.librarie.lore;

import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.entity.librarie.book.Book;
import com.examplenewstack.newstack.entity.librarie.collection.Collection;
import com.examplenewstack.newstack.entity.loan.Loan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lore")

//Getters and Setters, Constructor and NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Lore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false,length = 20)
    private String isbn;

    @Column(nullable = false)
    private int year_publication;

    @Column(nullable = false,length = 25)
    private String category;

    @Column(nullable = false)
    private Boolean availability;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime register;


    //Relacionamento com Employee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // Relacionamento com Collection
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    //Relacionamento 1:1 book
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    //Relacionamento com Loan
    @OneToMany(mappedBy = "lore",fetch = FetchType.LAZY)
    private List<Loan> loans;


}