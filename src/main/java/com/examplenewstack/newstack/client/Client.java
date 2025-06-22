package com.examplenewstack.newstack.client;

import com.examplenewstack.newstack.core.entity.User;
import com.examplenewstack.newstack.loan.Loan;
import com.examplenewstack.newstack.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class Client extends User {
    // Um cliente tem um único endereço (1:1)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    // Um cliente pode ter vários empréstimos (1:N)
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Loan> loans;
}
