package com.examplenewstack.newstack.employee.controller.api;

import com.examplenewstack.newstack.employee.service.ReportsEmployeeByIdService;
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
            @PathVariable Long id
    ){
        return ResponseEntity.ok().body(service.reportsEmployeeById(id));
    }
}
