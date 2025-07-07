package com.examplenewstack.newstack.pages.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class LoginView {

    @GetMapping("/v1/login")
    public ModelAndView loginScreen(){
        return new ModelAndView("login");
    }
}