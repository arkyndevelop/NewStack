package com.examplenewstack.newstack.domain.client.controller.view;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clients")
public class RegisterClientView {

    @GetMapping("/register")
    public ModelAndView clientRegisterView(){
        return new ModelAndView("register");
    }
}
