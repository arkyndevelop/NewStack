package com.examplenewstack.newstack.address.service;

import com.examplenewstack.newstack.address.Address;
import com.examplenewstack.newstack.address.dto.AddressDTO;
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
            AddressDTO addressDTO,
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

         address.setStreet(addressDTO.getStreet());
         address.setNumber_house(addressDTO.getNumber_house());
         address.setNeighborhood(addressDTO.getNeighborhood());
         address.setComplement(address.getComplement());
         address.setCep(addressDTO.getCep());
         address.setCity(addressDTO.getCity());
         address.setState(addressDTO.getState());
         address.setCountry(addressDTO.getCountry());

         Address updateAddress = addressRepository.save(address);

         return ResponseEntity.ok(updateAddress);
    }
}
