package com.examplenewstack.newstack.address.service;


import com.examplenewstack.newstack.address.Address;
import com.examplenewstack.newstack.address.dto.AddressRequest;
import com.examplenewstack.newstack.address.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterAddressService {

    private final AddressRepository addressRepository;

    public RegisterAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address registerAddress(
            AddressRequest request
    ) {
        return addressRepository.save(request.toAddress());
    }
}
