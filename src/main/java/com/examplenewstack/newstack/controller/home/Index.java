package com.examplenewstack.newstack.controller.home;


import com.examplenewstack.newstack.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // Define que esta classe é um Controller Spring
@RequestMapping("/home")// Mapeia todas as rotas deste controller para '/home'
public class Index {

    private final UserRepository userRepository; // Injeção de dependência do UserRepository
    // Construtor para injeção de dependência
    public Index(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/index")// Manipula requisições GET para '/home/index'
    public ModelAndView indexScreen(HttpSession session) {
        if (session.getAttribute("LoginFeito") == null) {
            return new ModelAndView("redirect:/home/login");
        }
        return new ModelAndView("index");
    }
}

