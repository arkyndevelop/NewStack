package com.examplenewstack.newstack.collection.controller.api;

import com.examplenewstack.newstack.collection.service.ReportAllCollection;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection")
@Tag(name = "Coleção")
public class ReportAllController {

    private final ReportAllCollection service;

    public ReportAllController(ReportAllCollection service) {
        this.service = service;
    }

    @GetMapping("/report/all")
    public ResponseEntity<?> reportAll(){
        return ResponseEntity.ok().body(service.reportAll());
    }
}
