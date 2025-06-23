package com.examplenewstack.newstack.domain.employee.controller.view;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class RegisterEmployeeView {


    @GetMapping("/registerEmployee")
    public ModelAndView registerEmployee(){
        return new ModelAndView("register");
    }
}
