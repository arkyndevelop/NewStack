package com.examplenewstack.newstack.controller.user.clientController.api;

import com.examplenewstack.newstack.entity.dto.clientdto.ClientDTO;
import com.examplenewstack.newstack.exception.ClientsException.CustomException;
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
