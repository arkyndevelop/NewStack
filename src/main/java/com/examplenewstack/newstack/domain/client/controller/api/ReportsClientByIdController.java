package com.examplenewstack.newstack.domain.client.controller.api;


import com.examplenewstack.newstack.domain.client.service.ReportsClientByIdService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@Tag(name = "Cliente")
public class ReportsClientByIdController {

    private final ReportsClientByIdService reportsClientByIdService;


    public ReportsClientByIdController(ReportsClientByIdService reportsClientByIdService) {
        this.reportsClientByIdService = reportsClientByIdService;
    }

    @GetMapping("/reportsBy/{id}")
    public ResponseEntity<?> showClientById(
            @PathVariable int id
    ){

        return ResponseEntity.ok().body(reportsClientByIdService.showClientById(id));
    }
}
