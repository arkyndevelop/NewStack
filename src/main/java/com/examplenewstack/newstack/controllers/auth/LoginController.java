package com.examplenewstack.newstack.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public ModelAndView loginScreen(){
        return new ModelAndView("login");
    }
}