package com.examplenewstack.newstack.address.controller.api;

import com.examplenewstack.newstack.address.service.ReportsAllAddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return ResponseEntity.ok().body(reportsAllAddressService.reportsAllAddress());
    }
}

// Esse Endpoint é para caso seja necessário verificar os endereços cadastrados de forma geral!