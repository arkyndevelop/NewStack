package com.examplenewstack.newstack.controller.user.clientController.api;


import com.examplenewstack.newstack.entity.dto.clientdto.ClientDTO;
import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.service.user.client.ShowClientByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ShowClientByIdController {

    private final ShowClientByIdService showClientByIdService;


    public ShowClientByIdController(ShowClientByIdService showClientByIdService) {
        this.showClientByIdService = showClientByIdService;
    }

    @GetMapping("/reportsBy/{id}")
    public ResponseEntity<?> showClientById( @PathVariable Long id) {

        try {
            return ResponseEntity.ok().body(showClientByIdService.showClientById(id));
        } catch (Exception e) {
            throw new CustomException("Erro: Nenhum cliente encontrado!");
        }


    }
}
