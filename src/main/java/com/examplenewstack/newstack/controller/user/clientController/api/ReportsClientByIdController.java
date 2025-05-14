package com.examplenewstack.newstack.controller.user.clientController.api;


import com.examplenewstack.newstack.exception.ClientsException.CustomException;
import com.examplenewstack.newstack.service.user.client.ReportsClientByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ReportsClientByIdController {

    private final ReportsClientByIdService reportsClientByIdService;


    public ReportsClientByIdController(ReportsClientByIdService reportsClientByIdService) {
        this.reportsClientByIdService = reportsClientByIdService;
    }

    @GetMapping("/reportsBy/{id}")
    public ResponseEntity<?> showClientById( @PathVariable Long id) {


            return ResponseEntity.ok().body(reportsClientByIdService.showClientById(id));



    }
}
