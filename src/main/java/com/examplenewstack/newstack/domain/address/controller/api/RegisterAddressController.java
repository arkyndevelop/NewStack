package com.examplenewstack.newstack.domain.address.controller.api;

import com.examplenewstack.newstack.domain.address.Address;
import com.examplenewstack.newstack.domain.address.dto.AddressRequest;
import com.examplenewstack.newstack.domain.address.service.RegisterAddressService;
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
    public ResponseEntity<?> registerAddress(
            @RequestBody AddressRequest request
    ) {

        Address address = registerAddressService.registerAddress(request);
        return ResponseEntity.ok().body(address);
    }
}


// Endpoint para ser possível acrescentar, junto do front, a opção dentro do perfil do Usuário para criar um endereço!