package com.examplenewstack.newstack.domain.client.controller.api;


import com.examplenewstack.newstack.domain.client.service.DeleteAllClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@Tag(name = "Cliente")
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
