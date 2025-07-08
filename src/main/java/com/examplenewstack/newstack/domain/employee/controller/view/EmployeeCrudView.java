package com.examplenewstack.newstack.domain.employee.controller.view;

import com.examplenewstack.newstack.domain.employee.dto.EmployeeResponse;
import com.examplenewstack.newstack.domain.employee.enums.TypeEmployee;
import com.examplenewstack.newstack.domain.employee.service.EmployeeCrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/v1/employees")
public class EmployeeCrudView {

    private final EmployeeCrudService service;

    // Injeção de dependência do serviço
    public EmployeeCrudView(EmployeeCrudService service) {
        this.service = service;
    }

    // Mapeamento para a página de registro de funcionário
    @GetMapping("/register")
    public ModelAndView showRegisterForm(){
        ModelAndView mav = new ModelAndView("registerEmployee");
        // Disponibiliza os valores do Enum 'TypeEmployee' para o formulário
        mav.addObject("employeeTypes", TypeEmployee.values());
        return mav;
    }

    // Mapeamento para a página que exibe todos os funcionários
    @GetMapping("/reports")
    public ModelAndView reportsAllEmployee(){
        ModelAndView mav = new ModelAndView("reportEmployee");
        List<EmployeeResponse> employeeList = service.reportsAllEmployee();
        mav.addObject("employeeList", employeeList);
        return mav;
    }
}