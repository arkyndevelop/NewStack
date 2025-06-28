package com.examplenewstack.newstack.domain.loan.controller.api;

import com.examplenewstack.newstack.domain.loan.Loan;
import com.examplenewstack.newstack.domain.loan.dto.LoanRequest;
import com.examplenewstack.newstack.domain.loan.service.RegisterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
@Tag(name = "Empréstimos")
public class LoanRegisterController {

    private final RegisterService service;

    public LoanRegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody @Valid LoanRequest request
    ) {
        Loan loan = service.register(request, request.clientId(), request.bookId());
        return ResponseEntity.ok().body(loan);
    }
}
