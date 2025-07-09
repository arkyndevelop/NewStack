package com.examplenewstack.newstack.domain.client.dto;

import com.examplenewstack.newstack.core.dto.UserResponseDTO;
import com.examplenewstack.newstack.domain.address.Address;
import com.examplenewstack.newstack.domain.client.Client;

import java.time.Instant;

public record ClientResponseProfileDetails(
        Integer id,
        String name,
        String CPF,
        String email,
        String telephone,
        Instant dateRegister,
        Address address
)  implements UserResponseDTO {

    public static ClientResponseProfileDetails fromEntity(
            Client client
    ) {
        return new ClientResponseProfileDetails(
                client.getId(),
                client.getName(),
                client.getCPF(),
                client.getEmail(),
                client.getTelephone(),
                client.getDateRegister(), // Passe o valor do campo
                client.getAddress()
        );
    }
}
