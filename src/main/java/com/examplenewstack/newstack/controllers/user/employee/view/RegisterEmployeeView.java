package com.examplenewstack.newstack.controllers.user.employee.view;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class RegisterEmployeeView {


    @PostMapping("/registerEmployee")
    public ModelAndView registerEmployee(){
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }
}
