package com.examplenewstack.newstack.domain.client.dto;

import com.examplenewstack.newstack.domain.address.dto.AddressUpdateRequest;

// DTO específico para a atualização de perfil pelo próprio cliente.
// Contém apenas os campos que o usuário pode alterar.
public record ClientProfileUpdateRequest(
        String name,
        String telephone,
        AddressUpdateRequest address
) {
}