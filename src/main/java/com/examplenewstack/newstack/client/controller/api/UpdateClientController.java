package com.examplenewstack.newstack.client.controller.api;


import com.examplenewstack.newstack.client.dto.ClientDTO;
import com.examplenewstack.newstack.client.service.UpdateClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@Tag(name = "Cliente")
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

        updateClientService.updateClient(clientDTO, id);
        return ResponseEntity.ok().build();

    }
}
