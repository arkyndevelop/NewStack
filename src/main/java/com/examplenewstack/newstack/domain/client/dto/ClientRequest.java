package com.examplenewstack.newstack.domain.client.dto;

import com.examplenewstack.newstack.core.dto.UserRequestDTO;
import com.examplenewstack.newstack.domain.client.Client;
import jakarta.validation.constraints.*;

import org.hibernate.validator.constraints.br.CPF;

public record ClientRequest(
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
        String password

) implements UserRequestDTO {

    public Client toClient() {
        Client client = new Client();
        client.setName(name);
        client.setCPF(CPF);
        client.setEmail(email);
        client.setTelephone(telephone);
        client.setPassword(password);
        client.setRole("CLIENT");
        return client;
    }
}
