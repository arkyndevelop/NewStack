package com.examplenewstack.newstack.controller.user.clientController.api;

import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.service.user.client.ReportsAllClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ReportsAllClientController {

    private final ReportsAllClientService reportsAllClientService;

    public ReportsAllClientController(ReportsAllClientService reportsAllClientService) {
        this.reportsAllClientService = reportsAllClientService;
    }

    @GetMapping("/reports/all")
    public ResponseEntity<?> showAllClients(){
        try {

            return ResponseEntity.ok().body(reportsAllClientService.findAllClients());
        } catch (Exception e){

           throw new CustomException("Erro: Nenhum cliente cadastrado encontrado!");


        }


    }
}
