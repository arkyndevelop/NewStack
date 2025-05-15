package com.examplenewstack.newstack.controllers.user.client.api;


import com.examplenewstack.newstack.dtos.client.ClientDTO;
import com.examplenewstack.newstack.entity.user.client.Client;
import com.examplenewstack.newstack.service.user.client.RegisterClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
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
