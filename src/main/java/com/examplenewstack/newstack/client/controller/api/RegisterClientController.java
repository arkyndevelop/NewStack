package com.examplenewstack.newstack.client.controller.api;


import com.examplenewstack.newstack.client.dto.ClientDTO;
import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.client.service.RegisterClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@Tag(name = "Cliente")
public class RegisterClientController {

    private final RegisterClientService registerClientService;

    public RegisterClientController(RegisterClientService registerClientService) {
        this.registerClientService = registerClientService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> clientRegister(@RequestBody ClientDTO clientDTO) {

        Client client = registerClientService.registerClient(clientDTO);
        return ResponseEntity.ok(client);

    }
}
