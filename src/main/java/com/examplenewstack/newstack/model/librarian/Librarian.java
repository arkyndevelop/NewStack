package com.examplenewstack.newstack.model.librarian;

import jakarta.persistence.*;
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

    @Column(name = "date_register", nullable = false)
    private LocalDateTime dateRegister;

    public Librarian() {}

    public Librarian(String name, String email, String telephone, String password) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.dateRegister = LocalDateTime.now();
    }

    // Getters and Setters
    public UUID getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }

    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getDateRegister() { return dateRegister; }

    public void setDateRegister(LocalDateTime dateRegister) { this.dateRegister = dateRegister; }
}
