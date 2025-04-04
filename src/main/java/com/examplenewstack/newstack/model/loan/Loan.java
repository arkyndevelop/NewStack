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

    @Column(nullable = false)
    private LocalDateTime date_loan;

    @Column(nullable = false)
    private LocalDateTime devolution_date;

    @Column(nullable = false)
    private LocalDateTime devolution_date_real;
    //private  status_loan;

}
