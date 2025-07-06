package com.examplenewstack.newstack.domain.book.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/books")
public class ReportBookByIDView {

//    @GetMapping("/reports/{id}")
//    public ModelAndView report(@PathVariable int id, int quantidade){
//        return new ModelAndView("reportBooks");
//    }
}
