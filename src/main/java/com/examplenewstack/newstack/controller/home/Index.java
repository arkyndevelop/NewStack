package com.examplenewstack.newstack.controller.home;


import com.examplenewstack.newstack.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
// Define que esta classe é um Controller Spring
@Controller
// Mapeia todas as rotas deste controller para '/home'
@RequestMapping("/home")
public class Index {
    // Injeção de dependência do UserRepository
    private final UserRepository userRepository;
    // Construtor para injeção de dependência
    public Index(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // Manipula requisições GET para '/home/index'
    @GetMapping("/index")
    public ModelAndView indexScreen(HttpSession session) {
        if (session.getAttribute("LoginFeito") == null) {
            return new ModelAndView("redirect:/home/login");
        }
        return new ModelAndView("index");
    }
}

