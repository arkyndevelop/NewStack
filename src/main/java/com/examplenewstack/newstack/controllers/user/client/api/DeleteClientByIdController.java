package com.examplenewstack.newstack.controllers.user.client.api;

import com.examplenewstack.newstack.dtos.client.ClientDTO;
import com.examplenewstack.newstack.service.user.client.DeleteClientByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class DeleteClientByIdController {

    private final DeleteClientByIdService deleteClientByIdService;


    public DeleteClientByIdController(DeleteClientByIdService deleteClientByIdService) {
        this.deleteClientByIdService = deleteClientByIdService;
    }


    @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteById(@RequestBody ClientDTO clientDTO , @PathVariable Long id){


                deleteClientByIdService.deleteById(id);
                return ResponseEntity.ok().build();


    }
}
