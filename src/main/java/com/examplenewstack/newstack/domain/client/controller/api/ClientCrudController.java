package com.examplenewstack.newstack.domain.client.controller.api;

import com.examplenewstack.newstack.domain.client.dto.ClientRequest;
import com.examplenewstack.newstack.domain.client.service.ClientCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@Tag(name = "Cliente")
public class ClientCrudController {

    private final ClientCrudService clientCrudService;

    public ClientCrudController(ClientCrudService clientCrudService) {
        this.clientCrudService = clientCrudService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> clientRegisterController(@RequestBody @Valid ClientRequest clientRequest) {
        return ResponseEntity.ok(clientCrudService.registerClient(clientRequest));
    }

    @GetMapping("/reports/all")
    public ResponseEntity<?> reportsAllClientsController(){
        return ResponseEntity.ok().body(clientCrudService.findAllClients());
    }

    @GetMapping("/reportsBy/{id}")
    public ResponseEntity<?> reportsClientByIdController(@PathVariable int id){
        return ResponseEntity.ok().body(clientCrudService.showClientById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClientController(@RequestBody ClientRequest clientRequest, @PathVariable int id) {

        clientCrudService.updateClient(clientRequest, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAllClientsController(){

        clientCrudService.deleteAllClients();
        return ResponseEntity.ok().build();


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteByIdController(@PathVariable int id) {
        return ResponseEntity.ok().body(clientCrudService.deleteByIdService(id));
    }
}
