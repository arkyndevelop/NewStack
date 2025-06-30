package com.examplenewstack.newstack.domain.client;

import com.examplenewstack.newstack.core.entity.User;
import com.examplenewstack.newstack.domain.loan.Loan;
import com.examplenewstack.newstack.domain.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class Client extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Client(String name, String CPF, String email, String telephone, String password, Instant dateRegister) {
        // A role "CLIENT" é fixada e passada para a superclasse User para ser persistida.
        super(name, CPF, email, telephone, password, dateRegister, "CLIENT");
    }

    // Um cliente tem um único endereço (1:1)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Loan> loans;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

}
