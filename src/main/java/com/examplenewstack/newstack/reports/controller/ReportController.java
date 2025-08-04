package com.examplenewstack.newstack.reports.controller;

import com.examplenewstack.newstack.reports.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    // Reports de Loan (inicio)
    @GetMapping("/loans/report-quantidade")
    public Map<String, Double> reportBook() {
        return service.reportByLoanAmount();
    }

    @GetMapping("/loans/report-by-month")
    public Map<String, Integer> reportQuantityLoansByMonth() {
        return service.reportByLoanMonth();
    }
    // Reports de Loan (fim)

    // Reports de Client (inicio)
    @GetMapping("/clients/reports-clients-per-month")
    public Map<String, Integer> reportRegisterClientByMonth() { return service.reportRegisterClientByMonth();}
    // Reports de Client (fim)
}
