package com.examplenewstack.newstack.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String CPF;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = true)
    private String telephone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateRegister;

    public User() {} // Utilizado apenas em alguns casos

    public User(String name, String CPF, String email, String telephone, String password) {
        this.name = name;
        this.CPF = CPF;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
    }

    public User(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }

    // Data é definida no momento da confirmação do cadastro
    @PrePersist
    private void onCreate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedNow = LocalDateTime.now().format(formatter);
        this.dateRegister = LocalDateTime.parse(formattedNow, formatter);
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public User toUser(){
        User user = new User();
        user.setName(this.name);
        user.setCPF(this.CPF);
        user.setEmail(this.email);
        user.setTelephone(this.telephone);
        user.setPassword(this.password);

        return user;
    }
}
