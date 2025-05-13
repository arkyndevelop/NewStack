package com.examplenewstack.newstack.entity.loan;

import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.entity.librarie.lore.Lore;
import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "loan")

//Getters and Setters, Constructor and NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime loanDate;

    @Column(nullable = false)
    private LocalDateTime expectedReturnDate;

    @Column(nullable = true)
    private LocalDateTime actualReturnDate;

    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    //Relacionamento com a tabela employee 1:n
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    //Relacionamento com a tabela lore 1:n
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lore_id", nullable = false)
    private Lore lore;
}
