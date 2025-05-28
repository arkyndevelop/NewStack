package com.examplenewstack.newstack.address.service;


import com.examplenewstack.newstack.address.dto.AddressDTO;
import com.examplenewstack.newstack.address.Address;
import com.examplenewstack.newstack.address.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegisterAddressService {

    private final AddressRepository addressRepository;

    public RegisterAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public Address registerAddres(@RequestBody AddressDTO addressDTO){

        return addressRepository.save(addressDTO.toAdress());



    }
}
