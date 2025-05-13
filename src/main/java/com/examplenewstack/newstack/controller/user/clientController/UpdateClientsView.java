package com.examplenewstack.newstack.controller.user.clientController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
