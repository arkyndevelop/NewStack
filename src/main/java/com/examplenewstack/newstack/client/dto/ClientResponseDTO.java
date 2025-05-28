package com.examplenewstack.newstack.client.dto;

import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.core.dto.UserResponseDTO;

public record ClientResponseDTO(
        Long id,
        String name,
        String CPF,
        String email,
        String telephone
) implements UserResponseDTO {

    public static ClientResponseDTO fromEntity(Client client) {
        return new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getCPF(),
                client.getEmail(),
                client.getTelephone()
        );
    }
}
