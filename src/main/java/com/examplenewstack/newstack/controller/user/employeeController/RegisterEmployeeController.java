package com.examplenewstack.newstack.controller.user.employeeController;

import com.examplenewstack.newstack.entity.dto.employeedto.EmployeeDTO;
import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.repository.EmployeeRepository;
import com.examplenewstack.newstack.service.user.employee.RegisterEmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/employee")
public class RegisterEmployeeController {

    private final RegisterEmployeeService registerEmployeeService;

    public RegisterEmployeeController(RegisterEmployeeService registerEmployeeService) {
        this.registerEmployeeService = registerEmployeeService;
    }



    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee( @RequestBody EmployeeDTO employeeDTO){

        try{

            Employee employee = registerEmployeeService.registerEmployee(employeeDTO);
            return ResponseEntity.ok(employee);
        } catch (Exception e){
            throw new CustomException("Erro: Dados de empregados ja cadastrado ou dados inválidos!");
        }
    }
}
