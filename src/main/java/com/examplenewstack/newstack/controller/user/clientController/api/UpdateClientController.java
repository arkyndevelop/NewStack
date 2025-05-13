package com.examplenewstack.newstack.controller.user.clientController.api;


import com.examplenewstack.newstack.entity.dto.clientdto.ClientDTO;
import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.service.user.client.UpdateClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class UpdateClientController {

    private final UpdateClientService updateClientService;

    public UpdateClientController(UpdateClientService updateClientService) {
        this.updateClientService = updateClientService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(
            @RequestBody ClientDTO clientDTO,
            @PathVariable Long id
    ) {
        try{
           updateClientService.updateClient(clientDTO, id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            throw new CustomException("Erro ao atualizar um cliente");
        }
    }
}
