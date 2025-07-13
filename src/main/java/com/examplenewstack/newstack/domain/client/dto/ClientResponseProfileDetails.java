package com.examplenewstack.newstack.domain.client.dto;

import com.examplenewstack.newstack.core.dto.UserResponseDTO;
import com.examplenewstack.newstack.domain.address.Address;
import com.examplenewstack.newstack.domain.address.dto.AddressResponse;
import com.examplenewstack.newstack.domain.client.Client;

import java.time.Instant;

public record ClientResponseProfileDetails(
        int id,
        String name,
        String CPF,
        String email,
        String telephone,
        Instant dateRegister,
        AddressResponse address
)  implements UserResponseDTO {

    public static ClientResponseProfileDetails fromEntity(
            Client client
    ) {

        AddressResponse addressDto = null;
        if (client.getAddress() != null) {
            // Pega a entidade Endereço
            addressDto = AddressResponse.fromEntity(client.getAddress());
        }

        return new ClientResponseProfileDetails(
                client.getId(),
                client.getName(),
                client.getCPF(),
                client.getEmail(),
                client.getTelephone(),
                client.getDateRegister(), // Passe o valor do campo
                addressDto
        );
    }
}
