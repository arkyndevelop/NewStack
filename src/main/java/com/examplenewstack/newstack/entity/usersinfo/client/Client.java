package com.examplenewstack.newstack.entity.usersinfo.client;

import com.examplenewstack.newstack.entity.User;
import com.examplenewstack.newstack.entity.loan.Loan;
import com.examplenewstack.newstack.entity.usersinfo.address.Address;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "client")
public class Client extends User {
    // Um cliente tem um único endereço (1:1)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    // Um cliente pode ter vários empréstimos (1:N)
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Loan> loans;

    public Client() { super(); }

    public Client(Instant dateRegister) {
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
