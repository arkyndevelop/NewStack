package com.examplenewstack.newstack.domain.book.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BorrowBookView {

    //Arrumar esse metodo de acordo com a view correta
//    @PostMapping("/borrow/{id}")
//    public ModelAndView borrowBook(@RequestParam("id") int id, @RequestParam("quantidade") int quantidade, RedirectAttributes redirectAttributes)
//    {
//        //Classe vinda de ReportBookByIDView
//        return report(id,quantidade);
//    }
}
