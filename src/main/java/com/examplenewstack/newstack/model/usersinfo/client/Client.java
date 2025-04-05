package com.examplenewstack.newstack.model.usersinfo.client;

import com.examplenewstack.newstack.model.User;
import com.examplenewstack.newstack.model.usersinfo.address.Address;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "client")
public class Client extends User {
    public Client() { super(); }

    public Client(String name, String CPF, String email, String telephone, String password) {
        super(name, CPF, email, telephone, password);
    }

    // Relação de Um para um com classe Endereço
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false, unique = true )
    private Address address;

    @Override
    public User toUser() {
        return super.toUser();
    }
}
