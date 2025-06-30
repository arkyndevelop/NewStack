package com.examplenewstack.newstack.domain.loan.controller;

import com.examplenewstack.newstack.domain.loan.dto.LoanRequest;
import com.examplenewstack.newstack.domain.loan.service.DeleteAllService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
@Tag(name = "Empréstimos")
public class LoanDeleteAllController {


    private final DeleteAllService deleteAllService;

    public LoanDeleteAllController(DeleteAllService deleteAllService) {
        this.deleteAllService = deleteAllService;
    }


    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAll(
            @RequestBody @Valid LoanRequest request
            ){

        deleteAllService.deleteAllLoan();
        return ResponseEntity.ok().build();


    }
}
