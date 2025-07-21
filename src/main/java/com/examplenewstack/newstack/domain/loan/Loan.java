package com.examplenewstack.newstack.domain.loan;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.loan.enums.StatusLoan;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



//Getters and Setters, Constructor and NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime loanDate;

    @Column(nullable = false)
    private LocalDateTime expectedReturnDate;

    @Column(nullable = true)
    private LocalDateTime actualReturnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusLoan status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference("client-loan")
    private Client client;

    //Relacionamento com a tabela book 1:n
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "book_id", nullable = false)
    @JsonBackReference("book-loan")

    private Book book;
}
