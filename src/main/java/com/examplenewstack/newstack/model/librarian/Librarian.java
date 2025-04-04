package com.examplenewstack.newstack.model.librarian;

import jakarta.persistence.*;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "librarian")
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime date_register;

    public Librarian() {}

    public Librarian(String name, String email, String telephone, String password, LocalDateTime date_register) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.date_register = date_register;
    }
}
