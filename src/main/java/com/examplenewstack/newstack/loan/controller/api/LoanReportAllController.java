package com.examplenewstack.newstack.loan.controller.api;

import com.examplenewstack.newstack.loan.service.ReportAllService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
@Tag(name = "Empréstimos")
public class LoanReportAllController {

    private final ReportAllService service;

    public LoanReportAllController(ReportAllService service) {
        this.service = service;
    }

    @GetMapping("/report/all")
    public ResponseEntity<?> reportAll(){
        return ResponseEntity.ok().body(service.reportAll());
    }
}
