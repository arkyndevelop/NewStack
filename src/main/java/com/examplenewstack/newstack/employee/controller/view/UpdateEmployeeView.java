package com.examplenewstack.newstack.employee.controller.view;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class UpdateEmployeeView {

    @PutMapping("/updateEmployee")
    public ModelAndView updateEmployee(){
        ModelAndView modelAndView = new ModelAndView("reports");
        return modelAndView;
    }
}
