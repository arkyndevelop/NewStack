package com.examplenewstack.newstack.service.user.adress;


import com.examplenewstack.newstack.dtos.adress.AddressDTO;
import com.examplenewstack.newstack.entity.user.address.Address;
import com.examplenewstack.newstack.repository.AddressRepository;
import org.springframework.http.ResponseEntity;
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
