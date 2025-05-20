package com.examplenewstack.newstack.service.user.adress;

import com.examplenewstack.newstack.dtos.adress.AddressDTO;
import com.examplenewstack.newstack.entity.user.address.Address;
import com.examplenewstack.newstack.exceptions.Address.AddressNotFoundException;
import com.examplenewstack.newstack.repository.AddressRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ReportsAllAddressService {


    private final AddressRepository addressRepository;


    public ReportsAllAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }




    public List<Address> reportsAllAddress(){

        List<Address> Adresslist = addressRepository.findAll();

        if(Adresslist.isEmpty()){
            throw new AddressNotFoundException();
        }

        return addressRepository.findAll();


    }




}
