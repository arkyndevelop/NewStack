package com.examplenewstack.newstack.controller.user.clientController.api;


import com.examplenewstack.newstack.exception.ClientsException.CustomException;
import com.examplenewstack.newstack.service.user.client.DeleteAllClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class DeleteAllClientController {

    private final DeleteAllClientService deleteAllClientService;


    public DeleteAllClientController(DeleteAllClientService deleteAllClientService) {
        this.deleteAllClientService = deleteAllClientService;
    }


    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAllClients(){

            deleteAllClientService.deleteAllClients();
            return ResponseEntity.ok().build();


    }
}
