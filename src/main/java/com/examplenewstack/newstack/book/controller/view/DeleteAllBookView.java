package com.examplenewstack.newstack.book.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/books")
public class DeleteAllBookView {

    @GetMapping("/delete/all")
    public ModelAndView delete(){
        return new ModelAndView("");
    }
}
