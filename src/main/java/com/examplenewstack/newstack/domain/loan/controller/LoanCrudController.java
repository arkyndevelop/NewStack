package com.examplenewstack.newstack.domain.loan.controller;

import com.examplenewstack.newstack.domain.loan.dto.LoanRequest;
import com.examplenewstack.newstack.domain.loan.service.LoanCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@RestController
@RequestMapping("/loans")
@Tag(name = "Empréstimos")
public class LoanCrudController {

    private final LoanCrudService service;

    public LoanCrudController(LoanCrudService loanService) {
        this.service = loanService;
    }

    @PostMapping("/register")
    public String registerLoan(LoanRequest loanRequest, RedirectAttributes redirectAttributes) {
        try {
            service.register(loanRequest);
            redirectAttributes.addFlashAttribute("successMessage", "Empréstimo registrado com sucesso!");
            return "redirect:/v1/loans/reports"; // Redireciona para a tela de relatórios
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao registrar empréstimo: " + e.getMessage());
            return "redirect:/v1/loans/register"; // Volta para a tela de registro em caso de erro
        }
    }

    @GetMapping("/report/all")
    public ResponseEntity<?> reportAllController(){
        return ResponseEntity.ok().body(service.reportAll());
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateController(@RequestBody @Valid LoanRequest request, @PathVariable int id) {
//        service.update(request, id);
//        return ResponseEntity.ok().build();
//    }

//    @DeleteMapping("/delete/all")
//    public ResponseEntity<?> deleteAllController(){
//        service.deleteAll();
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteByIdController(@PathVariable int id){
        service.delete(id);
        return  ResponseEntity.ok().build();
    }


}
