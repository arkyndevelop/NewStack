package com.examplenewstack.newstack.address.dto;

import com.examplenewstack.newstack.address.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressRequest(

        @NotBlank(message = "Nome da rua não pode estar vazio!")
        @Size(max = 180)
        String street,

        @NotBlank(message = "Numero da Residencia não pode estar vazio!")
        String number_house,

        @NotBlank(message = "O bairro não pode estar vazio!")
        @Size(max = 110)
        String neighborhood,

        @NotBlank(message = "Complemento não pode estar vazio!")
        String complement,

        @NotBlank(message = "CEP não pode estar vazio!")
        @Size(max = 10)
        String cep,

        @NotBlank(message = "A cidade não pode estar vazia!")
        @Size(max = 220)
        String city,

        @NotBlank(message = "O Estado não pode estar vazio!")
        @Size(max = 75)
        String state,

        @NotBlank(message = "O país não pode estar vazio!")
        @Size(max = 65)
        String country
) {
    public Address toAddress() {
        Address address = new Address();

        address.setStreet(this.street);
        address.setNumber_house(this.number_house);
        address.setNeighborhood(this.neighborhood);
        address.setComplement(this.complement);
        address.setCep(this.cep);
        address.setCity(this.city);
        address.setState(this.state);
        address.setCountry(this.country);

        return address;
    }
}
