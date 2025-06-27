package com.examplenewstack.newstack.domain.client.controller.api;

import com.examplenewstack.newstack.domain.client.dto.ClientRequestDTO;
import com.examplenewstack.newstack.domain.client.service.DeleteClientByIdService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@Tag(name = "Cliente")
public class DeleteClientByIdController {

    private final DeleteClientByIdService deleteClientByIdService;

    public DeleteClientByIdController(DeleteClientByIdService deleteClientByIdService) {
        this.deleteClientByIdService = deleteClientByIdService;
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@RequestBody ClientRequestDTO clientRequestDTO, @PathVariable Long id) {

        deleteClientByIdService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
