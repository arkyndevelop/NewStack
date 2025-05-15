package com.examplenewstack.newstack.controllers.user.client.view;


import com.examplenewstack.newstack.entity.user.client.Client;
import com.examplenewstack.newstack.service.user.client.ReportsAllClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/clients")
public class ShowAllClientView {

    private final ReportsAllClientService reportsAllClientService;

    public ShowAllClientView(ReportsAllClientService reportsAllClientService) {
        this.reportsAllClientService = reportsAllClientService;
    }



    @GetMapping("/showClients/all")
    public ModelAndView showAllClients(){

        List<Client> existing = this.reportsAllClientService.findAllClients();

        ModelAndView modelAndView = new ModelAndView("reportClient");
        modelAndView.addObject("clietlist", existing);
        return modelAndView;

    }
}
