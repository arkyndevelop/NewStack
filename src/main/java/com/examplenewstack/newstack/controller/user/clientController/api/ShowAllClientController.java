package com.examplenewstack.newstack.controller.user.clientController.api;

import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.service.user.client.ShowAllClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ShowAllClientController {

    private final ShowAllClientService showAllClientService;

    public ShowAllClientController(ShowAllClientService showAllClientService) {
        this.showAllClientService = showAllClientService;
    }

    @GetMapping("/show/all")
    public ResponseEntity<?> showAllClients(){
        try {

            return ResponseEntity.ok().body(showAllClientService.findAllClients());
        } catch (Exception e){

           throw new CustomException("Erro ao exibir todos os clientes");


        }


    }
}
