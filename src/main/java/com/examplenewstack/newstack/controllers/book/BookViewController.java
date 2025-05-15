package com.examplenewstack.newstack.controllers.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/books")
public class BookViewController {

    @GetMapping("/register")
    public ModelAndView registerScreenBook(){
        return new ModelAndView("admMaster");
    }
}
