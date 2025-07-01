package com.examplenewstack.newstack.domain.client.dto;

import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.core.dto.UserResponseDTO;

public record ClientResponse(
        Integer id,
        String name,
        String CPF,
        String email,
        String telephone
) implements UserResponseDTO {

    public static ClientResponse fromEntity(
            Client client
    ){
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getCPF(),
                client.getEmail(),
                client.getTelephone()
        );
    }
}
