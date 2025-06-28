package com.examplenewstack.newstack.domain.book.controller.api;

import com.examplenewstack.newstack.domain.book.service.ReportAllBooksService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Tag(name = "Livros")
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
