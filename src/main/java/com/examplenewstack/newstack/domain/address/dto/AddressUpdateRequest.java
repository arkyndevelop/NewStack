package com.examplenewstack.newstack.domain.address.dto;

// DTO específico para receber dados de atualização de endereço do formulário de perfil.
public record AddressUpdateRequest(
        String street,
        String number_house,
        String neighborhood,
        String complement,
        String cep,
        String city,
        String state,
        String country
) {
}