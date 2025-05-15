package com.examplenewstack.newstack.controllers.user.client;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterClientView {

    @PostMapping("/auth")
    public ModelAndView clientRegisterView(){
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }
}
