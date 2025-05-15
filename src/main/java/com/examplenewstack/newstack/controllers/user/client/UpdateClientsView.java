package com.examplenewstack.newstack.controllers.user.client;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clients")
public class UpdateClientsView {


    public ModelAndView updateClients(){
        ModelAndView modelAndView = new ModelAndView("reportClient");
        return modelAndView;
    }
}
