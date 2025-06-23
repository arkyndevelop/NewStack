package com.examplenewstack.newstack.domain.client.controller.view;


import com.examplenewstack.newstack.domain.client.service.ReportsAllClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/clients")
public class ShowAllClientView {

    private final ReportsAllClientService reportsAllClientService;

    public ShowAllClientView(ReportsAllClientService reportsAllClientService) {
        this.reportsAllClientService = reportsAllClientService;
    }

    @GetMapping("/showClients/all")
    public ModelAndView showAllClients(){
        ModelAndView modelAndView = new ModelAndView("reportClient");
        return modelAndView;
    }
}
