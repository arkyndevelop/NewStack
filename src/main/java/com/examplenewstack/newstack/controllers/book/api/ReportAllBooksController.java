package com.examplenewstack.newstack.controllers.book.api;

import com.examplenewstack.newstack.service.book.ReportAllBooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class ReportAllBooksController {

    private final ReportAllBooksService reportAllBooksService;

    public ReportAllBooksController(ReportAllBooksService reportAllBooksService) {
        this.reportAllBooksService = reportAllBooksService;
    }

    @GetMapping("/reports/all")
    public ResponseEntity<?> reportAll(){
        return ResponseEntity.ok().body(reportAllBooksService.reportAllBooks());
    }
}
