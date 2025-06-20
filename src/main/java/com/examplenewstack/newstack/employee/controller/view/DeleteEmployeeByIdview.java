package com.examplenewstack.newstack.employee.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class DeleteEmployeeByIdview {


    @GetMapping("/delete")
    public ModelAndView deleteEmployeeById(){
        return new ModelAndView("reportEmployee");
    }
}
