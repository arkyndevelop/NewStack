package com.examplenewstack.newstack.domain.book.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/books")
public class ReportAllBookView {

    @GetMapping("/reports")
    public ModelAndView report(){
        return new ModelAndView("reportBooks");
    }
}
