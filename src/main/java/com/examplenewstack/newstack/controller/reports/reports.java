package com.examplenewstack.newstack.controller.reports;

import com.examplenewstack.newstack.model.usersinfo.client.Client;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class reports {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/reports")
    public ModelAndView reports(){
        List<Client> clientList = this.clientRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("reports");
        modelAndView.addObject("clientList", clientList);
        return modelAndView;
    }




}
