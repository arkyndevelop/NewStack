package com.examplenewstack.newstack.domain.address.controller.api;

import com.examplenewstack.newstack.domain.address.dto.AddressRequest;
import com.examplenewstack.newstack.domain.address.service.UpdateAddressService;
import com.examplenewstack.newstack.domain.client.Client;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@Tag(name = "Endereço")
public class UpdateAddressByIdController {


    private final UpdateAddressService updateAddressService;

    public UpdateAddressByIdController(UpdateAddressService updateAddressService) {
        this.updateAddressService = updateAddressService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAddressById(
            @RequestBody AddressRequest request,
            @RequestBody Client client,
            @PathVariable Long id
    ){
        return ResponseEntity.ok().body(updateAddressService.updateAddress(request,client,id));
    }
}
