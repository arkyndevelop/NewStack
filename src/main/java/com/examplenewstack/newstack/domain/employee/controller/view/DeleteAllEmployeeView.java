package com.examplenewstack.newstack.domain.employee.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class DeleteAllEmployeeView {

    @GetMapping("/delete/all")
    public ModelAndView deleteAllEmployee(){
        return new ModelAndView("reports");
    }
}
