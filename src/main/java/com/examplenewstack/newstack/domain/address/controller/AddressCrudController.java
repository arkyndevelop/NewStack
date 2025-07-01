package com.examplenewstack.newstack.domain.address.controller;

import com.examplenewstack.newstack.domain.address.Address;
import com.examplenewstack.newstack.domain.address.dto.AddressRequest;
import com.examplenewstack.newstack.domain.address.dto.AddressResponse;
import com.examplenewstack.newstack.domain.address.service.AddressCrudService;
import com.examplenewstack.newstack.domain.client.Client;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@Tag(name = "Endereço")
public class AddressCrudController {

    private final AddressCrudService service;

    public AddressCrudController(AddressCrudService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAddress(@RequestBody AddressRequest request) {
        Address address = service.registerAddress(request);
        return ResponseEntity.ok().body(address);
    }

    @GetMapping("/reportsAddress/all")
    public ResponseEntity<?> reportsAllAddress() {
        List<Address> addressList = this.service.reportsAllAddress();
        List<AddressResponse> addressResponses = addressList.stream().map(AddressResponse::fromEntity).toList();

        return ResponseEntity.ok(addressResponses);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAddressById(@RequestBody AddressRequest request, @RequestBody Client client, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.updateAddress(request, client, id));
    }
}


