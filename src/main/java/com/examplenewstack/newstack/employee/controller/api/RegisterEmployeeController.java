package com.examplenewstack.newstack.employee.controller.api;

import com.examplenewstack.newstack.employee.dto.EmployeeRequestDTO;
import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.service.RegisterEmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Tag(name = "Funcionário")
public class RegisterEmployeeController {

    private final RegisterEmployeeService service;

    public RegisterEmployeeController(RegisterEmployeeService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(
            @RequestBody @Valid EmployeeRequestDTO employeeDTO
    ){

        Employee employee = service.registerEmployee(employeeDTO);
        return ResponseEntity.ok(employee);
    }
}
