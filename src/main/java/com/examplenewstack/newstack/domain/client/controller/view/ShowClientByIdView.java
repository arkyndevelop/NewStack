package com.examplenewstack.newstack.domain.client.controller.view;


import com.examplenewstack.newstack.domain.client.service.ReportsClientByIdService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShowClientByIdView {

    private final ReportsClientByIdService reportsClientByIdService;

    public ShowClientByIdView(ReportsClientByIdService reportsClientByIdService) {
        this.reportsClientByIdService = reportsClientByIdService;
    }


    @GetMapping("/report/{id}")
    public ModelAndView showClientById(@PathVariable Long id){

        ModelAndView modelAndView = new ModelAndView("reportClient");
        return modelAndView;
    }
}
