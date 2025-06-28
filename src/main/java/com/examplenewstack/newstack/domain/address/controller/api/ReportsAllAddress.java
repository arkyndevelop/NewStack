package com.examplenewstack.newstack.domain.address.controller.api;

import com.examplenewstack.newstack.domain.address.Address;
import com.examplenewstack.newstack.domain.address.dto.AddressResponse;
import com.examplenewstack.newstack.domain.address.service.ReportsAllAddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
@Tag(name = "Endereço")
public class ReportsAllAddress {

    private final ReportsAllAddressService reportsAllAddressService;

    public ReportsAllAddress(ReportsAllAddressService reportsAllAddressService) {
        this.reportsAllAddressService = reportsAllAddressService;
    }

    @GetMapping("/reportsAddress/all")
    public ResponseEntity<?> reportsAllAddress(){

        List<Address>addressList=this.reportsAllAddressService.reportsAllAddress();
        List<AddressResponse>addressResponses=addressList.stream().map(AddressResponse::fromEntity).toList();
        return ResponseEntity.ok(addressResponses);
    }
}

// Esse Endpoint é para caso seja necessário verificar os endereços cadastrados de forma geral!