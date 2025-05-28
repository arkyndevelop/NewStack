package com.examplenewstack.newstack.client.dto;

import com.examplenewstack.newstack.core.dto.UserRequestDTO;
import com.examplenewstack.newstack.client.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

public record ClientRequestDTO(
        @NotBlank(message = "Nome não pode estar vazio!")
        @Size(min = 3, max = 60)
        String name,

        @NotBlank(message = "CPF não pode estar vazio!")
        @CPF(message = "CPF inválido!")
        String CPF,

        @NotBlank(message = "E-mail não pode estar vazio!")
        @Email(message = "E-mail inválido!")
        @Size(min = 10, max = 64)
        String email,

        @Size(max = 14)
        String telephone,

        @NotBlank(message = "Senha não pode estar vazia!")
        @Size(min = 8, max = 85)
        String password,

        @NotBlank(message = "Confirmação de senha não pode estar vazia!")
        String confirmPassword
) implements UserRequestDTO {

    public Client toUser() {
        Client client = new Client();
        client.setName(name);
        client.setCPF(CPF);
        client.setEmail(email);
        client.setTelephone(telephone);
        client.setPassword(password);
        return client;
    }
}
