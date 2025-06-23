package com.examplenewstack.newstack.domain.loan.controller.api;

import com.examplenewstack.newstack.domain.loan.service.DeleteByIdService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
@Tag(name = "Empréstimos")
public class LoanDeleteByIdController {

    private final DeleteByIdService deleteByIdService;

    public LoanDeleteByIdController(DeleteByIdService deleteByIdService) {
        this.deleteByIdService = deleteByIdService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(
            @PathVariable int id
    ){
        return  ResponseEntity.ok().body(deleteByIdService.deleteById(id));
    }
}
