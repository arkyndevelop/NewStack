package com.examplenewstack.newstack.controllers.user.employee.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class DeleteEmployeeByIdview {


    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteEmployeeById(){
        ModelAndView modelAndView = new ModelAndView("reports");
        return modelAndView;
    }
}
