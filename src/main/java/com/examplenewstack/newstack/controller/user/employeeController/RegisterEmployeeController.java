package com.examplenewstack.newstack.controller.user.employeeController;

import com.examplenewstack.newstack.entity.dto.employeedto.EmployeeDTO;
import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.exception.ClientsException.CustomException;
import com.examplenewstack.newstack.service.user.employee.RegisterEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
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
