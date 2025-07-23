package com.examplenewstack.newstack.domain.employee.controller;

import com.examplenewstack.newstack.domain.employee.dto.EmployeeRequest;
import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.service.EmployeeCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@Tag(name = "Funcionário")
public class EmployeeCrudController {

    private final EmployeeCrudService service;

    public EmployeeCrudController(EmployeeCrudService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody @Valid EmployeeRequest employeeDTO){

        Employee employee = service.registerEmployee(employeeDTO);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/reports/all")
    public ResponseEntity<?> reports(){
        return ResponseEntity.ok().body(service.findAllEmployees());
    }

    @GetMapping("/reportsBy/{id}")
    public ResponseEntity<?> reportsEmployeeById(@PathVariable int id){
        return ResponseEntity.ok().body(service.getEmployeeProfileById(id));
    }
}
