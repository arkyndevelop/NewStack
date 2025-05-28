package com.examplenewstack.newstack.employee.controller.api;

import com.examplenewstack.newstack.employee.dto.EmployeeDTO;
import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.service.RegisterEmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Tag(name = "Funcionário")
public class RegisterEmployeeController {

    private final RegisterEmployeeService registerEmployeeService;

    public RegisterEmployeeController(RegisterEmployeeService registerEmployeeService) {
        this.registerEmployeeService = registerEmployeeService;
    }



    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee( @RequestBody EmployeeDTO employeeDTO){


            Employee employee = registerEmployeeService.registerEmployee(employeeDTO);
            return ResponseEntity.ok(employee);


    }
}
