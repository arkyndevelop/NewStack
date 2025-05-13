package com.examplenewstack.newstack.controller.user.clientController;


import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import com.examplenewstack.newstack.service.user.client.ShowAllClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/clients")
public class ShowAllClientView {

    private final ShowAllClientService showAllClientService;

    public ShowAllClientView(ShowAllClientService showAllClientService) {
        this.showAllClientService = showAllClientService;
    }



    @GetMapping("/showClients/all")
    public ModelAndView showAllClients(){

        List<Client> existing = this.showAllClientService.findAllClients();

        ModelAndView modelAndView = new ModelAndView("reportClient");
        modelAndView.addObject("clietlist", existing);
        return modelAndView;

    }
}
