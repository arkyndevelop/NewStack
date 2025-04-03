package com.examplenewstack.newstack.controller.home;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class Index {
    @GetMapping("/index")
    public ModelAndView indexScreen(HttpServletRequest request, Model model){
        ModelAndView modelAndView =  new ModelAndView("index");
        return  modelAndView;
    }
}
