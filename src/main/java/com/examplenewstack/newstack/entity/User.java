package com.examplenewstack.newstack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,length = 60)
    private String name;

    @Column(nullable = false,unique = true,length = 18)
    private String CPF;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = true,length = 18)
    private String telephone;

    @Column(nullable = false,length = 85)
    private String password;

    @Column(nullable = false, updatable = false)
    private Instant dateRegister = Instant.now();



    public User() {} // Utilizado apenas em alguns casos

    public User(String name, String CPF, String email, String telephone, String password) {
        this.name = name;
        this.CPF = CPF;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
    }

    public User(Instant dateRegister) {
        this.dateRegister = dateRegister;
    }


    // Data é definida no momento da confirmação do cadastro
    @PrePersist
    private void onCreate() {

    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

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

    public Instant getDateRegister() {
        return dateRegister;
    }

    // Adicione um novo getter para a data formatada
    public String getFormattedDateRegister() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                .withZone(ZoneId.systemDefault());
        return formatter.format(dateRegister);
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
