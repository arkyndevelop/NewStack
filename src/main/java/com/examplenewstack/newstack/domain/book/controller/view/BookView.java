package com.examplenewstack.newstack.domain.book.controller.view;

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