package com.examplenewstack.newstack.reports.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class ReportView {

    // Reports de Loan (inicio)
    @GetMapping("/v1/loans/report-quantidade")
    public ModelAndView reportBooks() {
        return new ModelAndView("reports");
    }

    @GetMapping("/v1/loans/report-by-month")
    public ModelAndView reportQuantityLoansByMonth() {
        return new ModelAndView("reports");
    }
    // Reports de Loan (fim)

    // Reports de Client (inicio)
    @GetMapping("/v1/clients/reports-clients-per-month")
    public ModelAndView reportRegisterClientByMonth() { return new ModelAndView("reports");}
    // Reports de Client (fim)

}
