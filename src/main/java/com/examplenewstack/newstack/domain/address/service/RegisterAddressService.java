package com.examplenewstack.newstack.domain.address.service;


import com.examplenewstack.newstack.domain.address.Address;
import com.examplenewstack.newstack.domain.address.dto.AddressRequest;
import com.examplenewstack.newstack.domain.address.repository.AddressRepository;
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
