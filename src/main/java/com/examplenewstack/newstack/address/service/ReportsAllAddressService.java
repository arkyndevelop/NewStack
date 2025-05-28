package com.examplenewstack.newstack.address.service;

import com.examplenewstack.newstack.address.Address;
import com.examplenewstack.newstack.address.exception.AddressNotFoundException;
import com.examplenewstack.newstack.address.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
