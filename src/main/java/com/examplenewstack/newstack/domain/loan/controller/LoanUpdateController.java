package com.examplenewstack.newstack.domain.loan.controller;

import com.examplenewstack.newstack.domain.loan.dto.LoanRequest;
import com.examplenewstack.newstack.domain.loan.service.UpdateService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
@Tag(name = "Empréstimos")
public class LoanUpdateController {

    private final UpdateService service;

    public LoanUpdateController(UpdateService service) {
        this.service = service;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @RequestBody @Valid LoanRequest request,
            @PathVariable int id
    ) {
        service.update(request, id);
        return ResponseEntity.ok().build();
    }
}
