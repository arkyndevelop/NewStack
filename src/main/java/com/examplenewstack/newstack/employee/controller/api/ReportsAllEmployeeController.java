package com.examplenewstack.newstack.employee.controller.api;

import com.examplenewstack.newstack.employee.service.ReportsAllEmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Tag(name = "Funcionário")
public class ReportsAllEmployeeController {
   private final ReportsAllEmployeeService reportsAllEmployeeService;

    public ReportsAllEmployeeController(ReportsAllEmployeeService reportsAllEmployeeService) {
        this.reportsAllEmployeeService = reportsAllEmployeeService;
    }

    @GetMapping("/reports/all")
    public ResponseEntity<?> reports(){

       return ResponseEntity.ok().body(reportsAllEmployeeService.findAllEmployee());


    }
}
