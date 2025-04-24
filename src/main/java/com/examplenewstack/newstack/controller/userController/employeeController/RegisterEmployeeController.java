package com.examplenewstack.newstack.controller.userController.employeeController;

import com.examplenewstack.newstack.model.dto.employeedto.EmployeeDTO;
import com.examplenewstack.newstack.model.employee.Employee;
import com.examplenewstack.newstack.repository.EmployeeRepository;
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

    private final EmployeeRepository employeeRepository;

    public RegisterEmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/register")
    public ModelAndView registerScreen(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult result){
        //verifica erros nos campos
        if(result.hasErrors()) {
            // Captura a mensagem de erro do CPF ou demais campos
            String errorMessage = result.getFieldError().getDefaultMessage();

            return ResponseEntity.badRequest().body(errorMessage);
        }

        Employee employee = employeeDTO.toUser();
        // Salva os dados do usuário cadastrado
        employeeRepository.save(employee);

        return ResponseEntity.ok().body(Map.of("message", "Funcionário cadastrado com sucesso"));
    }
}
