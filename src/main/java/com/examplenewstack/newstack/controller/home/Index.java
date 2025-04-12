package com.examplenewstack.newstack.controller.home;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // Define que esta classe é um Controller Spring
@RequestMapping("/v1")// Mapeia todas as rotas deste controller para '/home'
public class Index {

    @GetMapping("/home")// Manipula requisições GET para '/home/index'
    public ModelAndView indexScreen(HttpSession session) {
//        if (session.getAttribute("LoginFeito") == null) {
//            return new ModelAndView("redirect:/home");
//        } Verificar retorno
        return new ModelAndView("index");
    }
}

