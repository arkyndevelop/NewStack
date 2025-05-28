package com.examplenewstack.newstack.address.controller.api;


import com.examplenewstack.newstack.dtos.adress.AddressDTO;
import com.examplenewstack.newstack.service.user.adress.DeleteAllAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class DeleteAllAddressController {

private final DeleteAllAddressService deleteAllAddressService;

    public DeleteAllAddressController(DeleteAllAddressService deleteAllAddressService) {
        this.deleteAllAddressService = deleteAllAddressService;
    }


    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAllAddress(@RequestBody AddressDTO addressDTO){

        deleteAllAddressService.deleteAllAddress(addressDTO);
        return  ResponseEntity.ok().build();

    }

}
