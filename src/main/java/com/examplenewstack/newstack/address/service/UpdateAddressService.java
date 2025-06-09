package com.examplenewstack.newstack.address.service;

import com.examplenewstack.newstack.address.Address;
import com.examplenewstack.newstack.address.dto.AddressRequest;
import com.examplenewstack.newstack.address.exception.AddressNotFoundException;
import com.examplenewstack.newstack.address.repository.AddressRepository;
import com.examplenewstack.newstack.client.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateAddressService {

    private final AddressRepository addressRepository;

    public UpdateAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }



    public ResponseEntity<Address> updateAddress(
            AddressRequest request,
            Client client,
            Long id
    ){
         Optional<Address> addressExists = addressRepository.findById(id);

        if(addressExists.isEmpty()){
            throw new AddressNotFoundException();
        }

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

         Address updateAddress = addressRepository.save(address);

         return ResponseEntity.ok(updateAddress);
    }
}
