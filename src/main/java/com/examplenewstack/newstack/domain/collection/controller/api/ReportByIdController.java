package com.examplenewstack.newstack.domain.collection.controller.api;

import com.examplenewstack.newstack.domain.collection.service.ReportByIdCollection;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection")
@Tag(name = "Coleção")
public class ReportByIdController {

    private final ReportByIdCollection service;

    public ReportByIdController(ReportByIdCollection service) {
        this.service = service;
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<?> reportById(
            @PathVariable int id
    ){

        return ResponseEntity.ok().body(service.reportById(id));
    }
}
