package com.examplenewstack.newstack.controllers.user.client;


import com.examplenewstack.newstack.entity.user.client.Client;
import com.examplenewstack.newstack.service.user.client.ReportsClientByIdService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ShowClientByIdView {

    private final ReportsClientByIdService reportsClientByIdService;

    public ShowClientByIdView(ReportsClientByIdService reportsClientByIdService) {
        this.reportsClientByIdService = reportsClientByIdService;
    }


    @GetMapping("/report/{id}")
    public ModelAndView showClientById(@PathVariable Long id){

       Optional<Client> clientList = this.reportsClientByIdService.showClientById(id);

        ModelAndView modelAndView = new ModelAndView("reportClient");
        modelAndView.addObject("clientlist", clientList);
        return modelAndView;
    }
}
