package com.examplenewstack.newstack.controller.userController.employeeController;


import com.examplenewstack.newstack.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteEmployeeController {

    private final EmployeeRepository employeeRepository;

    public DeleteEmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @DeleteMapping("/deleteEmployee{CPF}")
    @Transactional
    public String deleteByCPF(@PathVariable String CPF) {
        try {
            employeeRepository.deleteByCPF(CPF);
            return "redirect:/reports";
        } catch (Exception e){
            return "redirect:/reports" + e.getMessage().replace(" " , "+");
        }
    }
}