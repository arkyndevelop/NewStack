package com.examplenewstack.newstack.loan.controller.api;

import com.examplenewstack.newstack.loan.Loan;
import com.examplenewstack.newstack.loan.dto.LoanRequest;
import com.examplenewstack.newstack.loan.service.RegisterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
@Tag(name = "Empréstimo")
public class RegisterController {

    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody @Valid LoanRequest request
    ) {
        Loan loan = service.register(request);
        return ResponseEntity.ok().body(loan);
    }
}
