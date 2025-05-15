package com.examplenewstack.newstack.controllers.user.employee.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class ReportsAllEmployeeView {

    @GetMapping("/show/all")
    public ModelAndView reportsAllEmployee(){
        ModelAndView modelAndView = new ModelAndView("reports");
        return modelAndView;
    }
}
