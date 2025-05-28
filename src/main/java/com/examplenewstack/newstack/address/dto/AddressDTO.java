package com.examplenewstack.newstack.address.dto;

import com.examplenewstack.newstack.address.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotBlank(message = "Nome da rua não pode estar vazio!")
    @Size(max = 180)
    private String street;

    @NotBlank(message = "Numero da Residencia não pode estar vazio!")
    private String number_house;


    @NotBlank(message = "O bairro não pode estar vazio!")
    @Size(max = 110)
    private String neighborhood;

    @NotBlank(message = "Complemento não pode estar vazio!")
    private String complement;

    @NotBlank(message = "CEP não pode estar vazio!")
    @Size(max = 10)
    private String cep;

    @NotBlank(message = "A cidade não pode estar vazia!")
    @Size(max = 220)
    private String city;

    @NotBlank(message = "O Estado não pode estar vazio!")
    @Size(max= 75)
    private String state;

    @NotBlank(message = "O pais não pode estar vazio!")
    @Size(max = 65)
    private String country;


    public Address toAdress(){
        Address address = new Address();

        address.setStreet(getStreet());
        address.setNumber_house(getNumber_house());
        address.setNeighborhood(getNeighborhood());
        address.setComplement(getComplement());
        address.setCep(getCep());
        address.setCity(getCity());
        address.setState(getState());
        address.setCountry(getCountry());

        return address;

    }










}
