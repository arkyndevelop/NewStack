package com.examplenewstack.newstack.client.controller.api;

import com.examplenewstack.newstack.client.service.ReportsAllClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@Tag(name = "Cliente")
public class ReportsAllClientController {

    private final ReportsAllClientService reportsAllClientService;

    public ReportsAllClientController(ReportsAllClientService reportsAllClientService) {
        this.reportsAllClientService = reportsAllClientService;
    }

    @GetMapping("/reports/all")
    public ResponseEntity<?> reportsAllClients(){

            return ResponseEntity.ok().body(reportsAllClientService.findAllClients());





    }
}
