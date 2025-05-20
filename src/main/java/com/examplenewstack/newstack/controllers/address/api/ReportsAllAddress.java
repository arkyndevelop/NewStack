package com.examplenewstack.newstack.controllers.address.api;

import com.examplenewstack.newstack.dtos.adress.AddressDTO;
import com.examplenewstack.newstack.entity.user.address.Address;
import com.examplenewstack.newstack.service.user.adress.ReportsAllAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
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
