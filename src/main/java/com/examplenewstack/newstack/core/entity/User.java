package com.examplenewstack.newstack.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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

    @Column
    private String role;

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