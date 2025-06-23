package com.examplenewstack.newstack.domain.client.controller.api;


import com.examplenewstack.newstack.domain.client.dto.ClientRequestDTO;
import com.examplenewstack.newstack.domain.client.service.UpdateClientService;
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
            @RequestBody ClientRequestDTO clientRequestDTO,
            @PathVariable Long id
    ) {

        updateClientService.updateClient(clientRequestDTO, id);
        return ResponseEntity.ok().build();
    }
}
