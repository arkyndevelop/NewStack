package com.examplenewstack.newstack.domain.employee.controller.view;

import com.examplenewstack.newstack.domain.employee.enums.TypeEmployee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class RegisterEmployeeView {

    @GetMapping("/register-form")
    public ModelAndView showRegisterForm(){
        ModelAndView mav = new ModelAndView("registerEmployee");
        // Disponibiliza os valores do Enum para o Thymeleaf
        mav.addObject("employeeTypes", TypeEmployee.values());
        return mav;
    }
}