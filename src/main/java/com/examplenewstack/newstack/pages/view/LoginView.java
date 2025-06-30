package com.examplenewstack.newstack.pages.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class LoginView {

    @GetMapping("/login")
    public ModelAndView loginScreen(){
        return new ModelAndView("login");
    }
}