package com.examplenewstack.newstack.model.user;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user")
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

    @Column(nullable = false)
    private Date date_register;

    public User() {
    }

    public User(String name, String CPF, String email, String telephone, String password) {
        this.name = name;
        this.CPF = CPF;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
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

}
