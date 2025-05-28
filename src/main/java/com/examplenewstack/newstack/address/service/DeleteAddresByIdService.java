package com.examplenewstack.newstack.address.service;


import com.examplenewstack.newstack.dtos.adress.AddressDTO;
import com.examplenewstack.newstack.dtos.book.BookDTO;
import com.examplenewstack.newstack.entity.user.address.Address;
import com.examplenewstack.newstack.exceptions.Address.AddressNotFoundException;
import com.examplenewstack.newstack.repository.AddressRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Service
public class DeleteAddresByIdService {


    private final AddressRepository addressRepository;

    public DeleteAddresByIdService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public ResponseEntity<Address> deleteAddressById(@RequestParam Long id) {

        Optional<Address> addressList = addressRepository.findById(id);
        if (addressList.isPresent()) {
            addressRepository.deleteById(id);
           return ResponseEntity.ok().build();

        }
        throw new AddressNotFoundException();
    }
}
