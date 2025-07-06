package com.examplenewstack.newstack.domain.book.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/books")
public class ReturnBookView {

    //Arrumar esse metodo de acordo com a view correta
//    @PostMapping("/return/{id}")
//    public ModelAndView returnBook(@PathVariable int id, int quantidade)
//    {
//        //Classe vinda de ReportBookByIDView
//        return report(id,quantidade);
//    }
}
