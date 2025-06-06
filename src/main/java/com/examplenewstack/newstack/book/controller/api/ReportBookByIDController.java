package com.examplenewstack.newstack.book.controller.api;

import com.examplenewstack.newstack.book.service.ReportBookByIDService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Tag(name = "Livros")
public class ReportBookByIDController {

    private final ReportBookByIDService reportBookByIDService;

    public ReportBookByIDController(ReportBookByIDService reportBookByIDService) {
        this.reportBookByIDService = reportBookByIDService;
    }

    @GetMapping("/reports/{id}")
    public ResponseEntity<?> report(
            @PathVariable int id
    ){
        return ResponseEntity.ok().body(reportBookByIDService.findByID(id));
    }
} //to testando no swagger
