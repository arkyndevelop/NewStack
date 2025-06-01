package com.examplenewstack.newstack.address.controller.api;

import com.examplenewstack.newstack.address.dto.AddressDTO;
import com.examplenewstack.newstack.address.Address;
import com.examplenewstack.newstack.address.service.RegisterAddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@Tag(name = "Endereço")
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
