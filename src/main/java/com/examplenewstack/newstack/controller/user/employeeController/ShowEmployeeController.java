package com.examplenewstack.newstack.controller.userController.employeeController;

import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@RequestMapping("/v1")
public class ShowEmployeeController {
    private final EmployeeRepository employeeRepository;

    public ShowEmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employee/reports")
    public ModelAndView reports(){
        List<Employee> employeeList = this.employeeRepository.findAll();

        // Organiza a Lista antes de exibi-la
        // Observação: Esse metodo pega nome por nome, realizando a comparação para depois organizar
        employeeList.sort(Comparator.comparing(Employee::getName));

        ModelAndView modelAndView = new ModelAndView("reportEmployee");
        modelAndView.addObject(employeeList);
        return modelAndView;
    }
}
