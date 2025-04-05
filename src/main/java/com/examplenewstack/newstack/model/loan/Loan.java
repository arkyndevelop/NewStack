package com.examplenewstack.newstack.model.loan;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column(name = "loan_date", nullable = false)
    private LocalDateTime loanDate;


    @Column(name = "expected_return_date", nullable = false)
    private LocalDateTime expectedReturnDate;


    @Column(name = "return_date")
    private LocalDateTime returnDate;


    @Column(nullable = false)
    private String status;

    @Deprecated
    public Loan() {}

    public Loan(LocalDateTime loanDate, LocalDateTime expectedReturnDate, LocalDateTime returnDate, String status) {
        this.loanDate = loanDate;
        this.expectedReturnDate = expectedReturnDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // Getters e Setters

    public UUID getId() {
        return id;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDateTime getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDateTime expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
