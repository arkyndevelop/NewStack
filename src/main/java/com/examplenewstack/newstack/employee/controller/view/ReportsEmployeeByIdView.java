package com.examplenewstack.newstack.employee.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class ReportsEmployeeByIdView {

    @GetMapping("/reports/{id}")
    public ModelAndView reportsEmployeeById(){
        ModelAndView modelAndView = new ModelAndView("reports");
        return modelAndView;

    }

}
