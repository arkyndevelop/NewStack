package com.examplenewstack.newstack.controller.user.clientController.api;


import com.examplenewstack.newstack.entity.dto.clientdto.ClientDTO;
import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import com.examplenewstack.newstack.exception.CustomException;
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
        try {
            Client client = registerClientService.registerClient(clientDTO);
            return ResponseEntity.ok(client);
        } catch (Exception e) {

            throw new CustomException("Erro: Dados de cliente ja cadastrado ou dados invalidos!");

        }
    }
}
