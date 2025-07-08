package com.examplenewstack.newstack.domain.loan.controller;

import com.examplenewstack.newstack.domain.loan.Loan;
import com.examplenewstack.newstack.domain.loan.dto.LoanRequest;
import com.examplenewstack.newstack.domain.loan.service.LoanCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
@Tag(name = "Empréstimos")
public class LoanCrudController {

    private final LoanCrudService service;

    public LoanCrudController(LoanCrudService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerController(@RequestBody @Valid LoanRequest request) {
        Loan loan = service.register(request, request.clientId(), request.bookId());
        return ResponseEntity.ok().body(loan);
    }

    @GetMapping("/report/all")
    public ResponseEntity<?> reportAllController(){
        return ResponseEntity.ok().body(service.reportAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateController(@RequestBody @Valid LoanRequest request, @PathVariable int id) {
        service.update(request, id);
        return ResponseEntity.ok().build();
    }

//    @DeleteMapping("/delete/all")
//    public ResponseEntity<?> deleteAllController(){
//        service.deleteAll();
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteByIdController(@PathVariable int id){
        return  ResponseEntity.ok().body(service.deleteById(id));
    }
}
