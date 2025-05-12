package com.examplenewstack.newstack.model.dto;

import  com.examplenewstack.newstack.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

// Classe exclusiva para transferência de dados de um formulário para o Data Base
// O mesmo garante que dados sensíveis, como ID, senha, dentre outros não seja exposto para possíveis ataques
public class UserDTO {
    @NotBlank(message = "Nome não pode estar vazio!")
    @Size(min = 3, max = 60)
    private String name;
    @NotBlank(message = "CPF não pode estar vazio!")
    @CPF(message = "CPF inválido!")
    private String CPF;
    @NotBlank(message = "E-mail não pode estar vazio!")
    @Email(message = "E-mail inválido!")
    @Size(min = 10, max = 64)
    private String email;
    @Size(max = 14)
    private String telephone;
    @NotBlank(message = "Senha não pode estar vazia!")
    @Size(min = 8, max = 85)
    private String password;
    @NotBlank(message = "Confirmação de senha não pode estar vazia!")

    private String confirmPassword;

    public UserDTO() {}

    public UserDTO(String name, String CPF, String email, String telephone, String password, String confirmPassword) {
        this.name = name;
        this.CPF = CPF;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.confirmPassword = confirmPassword;
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

    public void setCpf(String CPF) {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // Metodo responsável por coletar as informações necessárias diretamente no formulário
    // Após coletar é passado para a devida Entidade (User)
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


