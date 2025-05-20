package com.examplenewstack.newstack.controllers.book.api;

import com.examplenewstack.newstack.service.book.ReportBookByIDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class ReportBookByIDController {

    private final ReportBookByIDService reportBookByIDService;

    public ReportBookByIDController(ReportBookByIDService reportBookByIDService) {
        this.reportBookByIDService = reportBookByIDService;
    }

    @GetMapping("/reports/{id}")
    public ResponseEntity<?> report(
            @PathVariable Long id
    ){
        return ResponseEntity.ok().body(reportBookByIDService.findByID(id));
    }
}
