package com.examplenewstack.newstack.domain.address.dto;

import com.examplenewstack.newstack.domain.address.Address;

public record AddressResponse(
        int id,
        String street,
        String number_house,
        String neighborhood,
        String complement,
        String cep,
        String city,
        String state,
        String country
) {
    public static AddressResponse fromEntity(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getStreet(),
                address.getNumber_house(),
                address.getNeighborhood(),
                address.getComplement(),
                address.getCep(),
                address.getCity(),
                address.getState(),
                address.getCountry()
        );
    }
}