package com.examplenewstack.newstack.entity.user.client;

import com.examplenewstack.newstack.entity.User;
import com.examplenewstack.newstack.entity.loan.Loan;
import com.examplenewstack.newstack.entity.user.address.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends User {

    // Um cliente tem um único endereço (1:1)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    // Um cliente pode ter vários empréstimos (1:N)
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Loan> loans;

    public Client() {
        super();
    }

    public Client(Instant dateRegister) {
        super(dateRegister);
    }

    public Client(String name, String CPF, String email, String telephone, String password) {
        super(name, CPF, email, telephone, password);
    }

    @Override
    public User toUser() {
        return super.toUser();
    }
}
