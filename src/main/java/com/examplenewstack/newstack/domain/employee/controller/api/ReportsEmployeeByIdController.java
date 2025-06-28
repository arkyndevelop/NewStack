package com.examplenewstack.newstack.domain.employee.controller.api;

import com.examplenewstack.newstack.domain.employee.service.ReportsEmployeeByIdService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@Tag(name = "Funcionário")
public class ReportsEmployeeByIdController {

    private final ReportsEmployeeByIdService service;


    public ReportsEmployeeByIdController(ReportsEmployeeByIdService service) {
        this.service = service;
    }

    @GetMapping("/reportsBy/{id}")
    public ResponseEntity<?> reportsEmployeeById(
            @PathVariable int id
    ){
        return ResponseEntity.ok().body(service.reportsEmployeeById(id));
    }
}
