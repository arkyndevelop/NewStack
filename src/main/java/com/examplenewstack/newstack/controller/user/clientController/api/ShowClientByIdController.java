package com.examplenewstack.newstack.controller.user.clientController.api;


import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.service.user.client.ShowClientByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ShowClientByIdController {

    private final ShowClientByIdService showClientByIdService;


    public ShowClientByIdController(ShowClientByIdService showClientByIdService) {
        this.showClientByIdService = showClientByIdService;
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> showClientById(@PathVariable Long id) {

        try {
            showClientByIdService.showClientById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new CustomException("Erro: Nenhum cliente encontrado!");
        }


    }
}
