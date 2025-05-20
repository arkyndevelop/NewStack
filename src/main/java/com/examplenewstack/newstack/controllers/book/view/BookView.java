package com.examplenewstack.newstack.controllers.book.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/books")
public class BookView {

    @GetMapping("/register")
    public ModelAndView registerScreenBook(){
        return new ModelAndView("registerBook");
    }
}