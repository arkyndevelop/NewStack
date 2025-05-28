package com.examplenewstack.newstack.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
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

    @Column(nullable = true,length = 18, unique = true)
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