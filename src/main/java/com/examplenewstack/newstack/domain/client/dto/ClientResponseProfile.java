package com.examplenewstack.newstack.domain.client.dto;

import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.core.dto.UserResponseDTO;
import java.time.Instant; // Importe a classe Instant

public record ClientResponseProfile(
        Integer id,
        String name,
        String CPF,
        String email,
        String telephone,
        Instant dateRegister // Adicione este campo
) implements UserResponseDTO {

    public static ClientResponseProfile fromEntity(
            Client client
    ){
        return new ClientResponseProfile(
                client.getId(),
                client.getName(),
                client.getCPF(),
                client.getEmail(),
                client.getTelephone(),
                client.getDateRegister() // Passe o valor do campo
        );
    }
}
