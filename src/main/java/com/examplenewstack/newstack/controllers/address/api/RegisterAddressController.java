package com.examplenewstack.newstack.controllers.address.api;

import com.examplenewstack.newstack.dtos.adress.AddressDTO;
import com.examplenewstack.newstack.entity.user.address.Address;
import com.examplenewstack.newstack.service.user.adress.RegisterAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class RegisterAddressController {


    private final RegisterAddressService registerAddressService;


    public RegisterAddressController(RegisterAddressService registerAddressService) {
        this.registerAddressService = registerAddressService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerAddress(@RequestBody AddressDTO addressDTO){


        Address address = registerAddressService.registerAddres(addressDTO);

        return ResponseEntity.ok().build();

    }
}
