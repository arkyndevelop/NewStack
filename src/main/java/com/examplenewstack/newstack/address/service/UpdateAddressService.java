package com.examplenewstack.newstack.address.service;

import com.examplenewstack.newstack.dtos.adress.AddressDTO;
import com.examplenewstack.newstack.entity.user.address.Address;
import com.examplenewstack.newstack.entity.user.client.Client;
import com.examplenewstack.newstack.exceptions.Address.AddressNotFoundException;
import com.examplenewstack.newstack.exceptions.client.NoCustomersFoundException;
import com.examplenewstack.newstack.repository.AddressRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UpdateAddressService {

    private final AddressRepository addressRepository;


    public UpdateAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }



    public ResponseEntity<Address> updateAddress(AddressDTO addressDTO, Client client, Long id){
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
