package com.examplenewstack.newstack.domain.address.service;


import com.examplenewstack.newstack.domain.address.Address;
import com.examplenewstack.newstack.domain.address.dto.AddressRequest;
import com.examplenewstack.newstack.domain.address.exception.AddressNotFoundException;
import com.examplenewstack.newstack.domain.address.repository.AddressRepository;
import com.examplenewstack.newstack.domain.client.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class AddressCrudService {

    private final AddressRepository addressRepository;

    public AddressCrudService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address registerAddress(AddressRequest request) {
        return addressRepository.save(request.toAddress());
    }

    public List<Address> reportsAllAddress(){
        List<Address> Adresslist = addressRepository.findAll();

        if(Adresslist.isEmpty()){
            throw new AddressNotFoundException();
        }
        return Adresslist;
    }

    public ResponseEntity<Address> updateAddress(AddressRequest request, Client client, Long id){
        Optional<Address> addressExists = addressRepository.findById(id);
        if(addressExists.isEmpty()){
            throw new AddressNotFoundException();
        }

        // Verifica se há algum endereço cadastrado para realizar o Update
        Address address = addressExists.get();
        if(!address.getClient().equals(client)){
            throw new AddressNotFoundException();
        }

        address.setStreet(request.street());
        address.setNumber_house(request.number_house());
        address.setNeighborhood(request.neighborhood());
        address.setComplement(address.getComplement());
        address.setCep(request.cep());
        address.setCity(request.city());
        address.setState(request.state());
        address.setCountry(request.country());

        // Salva o novo endereço
        Address updateAddress = addressRepository.save(address);

        return ResponseEntity.ok(updateAddress);
    }

    public ResponseEntity<Address> deleteAddressById(@RequestParam Long id) {
        Optional<Address> addressList = addressRepository.findById(id); //Procura o Endereço pelo ID

        if (addressList.isPresent()) {
            addressRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new AddressNotFoundException();
    }
}
