package com.examplenewstack.newstack.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;


public class User {
    @NotBlank(message = "Nome não pode ficar em branco!")
    private String name;

    @NotBlank(message = "CPF Inválido!")
    @NotNull
    @CPF
    private String cpf;
    @NotBlank(message = "E-mail invalido")
    private String email;

    private String telefone;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;


    private LocalDateTime date_register;

    @Deprecated
    public User(){}

    public User(String name, String cpf, String email, String telefone, String password, String confirmPassword) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public com.examplenewstack.newstack.model.user.User toUser(){
        com.examplenewstack.newstack.model.user.User user = new com.examplenewstack.newstack.model.user.User();
        user.setName(this.name);
        user.setCPF(this.cpf);
        user.setEmail(this.email);
        user.setTelephone(this.telefone);
        user.setPassword(this.password);
        user.setDate_register(LocalDateTime.now());

        return user;
    }
}

