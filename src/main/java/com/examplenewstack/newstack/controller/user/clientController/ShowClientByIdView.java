package com.examplenewstack.newstack.controller.user.clientController;


import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import com.examplenewstack.newstack.service.user.client.ShowClientByIdService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ShowClientByIdView {

    private final ShowClientByIdService showClientByIdService;

    public ShowClientByIdView(ShowClientByIdService showClientByIdService) {
        this.showClientByIdService = showClientByIdService;
    }


    @GetMapping("/Showclients/{id}")
    public ModelAndView showClientById(@PathVariable Long id){

       Optional<Client> clientList = this.showClientByIdService.showClientById(id);

        ModelAndView modelAndView = new ModelAndView("reportClient");
        modelAndView.addObject("clientlist", clientList);
        return modelAndView;
    }
}
