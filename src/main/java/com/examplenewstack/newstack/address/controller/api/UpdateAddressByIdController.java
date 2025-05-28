package com.examplenewstack.newstack.address.controller.api;

import com.examplenewstack.newstack.dtos.adress.AddressDTO;
import com.examplenewstack.newstack.entity.user.client.Client;
import com.examplenewstack.newstack.service.user.adress.UpdateAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class UpdateAddressByIdController {


    private final UpdateAddressService updateAddressService;

    public UpdateAddressByIdController(UpdateAddressService updateAddressService) {
        this.updateAddressService = updateAddressService;
    }


    public ResponseEntity<?> updateAddressById(@RequestBody AddressDTO addressDTO, @RequestBody Client client, @PathVariable Long id){

        return ResponseEntity.ok().body(updateAddressService.updateAddress(addressDTO,client,id));


    }
}
