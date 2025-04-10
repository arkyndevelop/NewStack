package com.examplenewstack.newstack.model.usersinfo.client;

import com.examplenewstack.newstack.model.User;
import com.examplenewstack.newstack.model.loan.Loan;
import com.examplenewstack.newstack.model.usersinfo.address.Address;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client")
public class Client extends User {
    // Um cliente tem um único endereço (1:1)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    // Um cliente pode ter vários empréstimos (1:N)
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans;

    public Client() { super(); }

    public Client(LocalDateTime dateRegister) {
        super(dateRegister);
    }

    public Client(String name, String CPF, String email, String telephone, String password) {
        super(name, CPF, email, telephone, password);
    }

    // Getters e Setters
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public User toUser() {
        return super.toUser();
    }



}
