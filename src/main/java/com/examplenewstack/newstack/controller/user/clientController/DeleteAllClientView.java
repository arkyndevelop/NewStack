package com.examplenewstack.newstack.controller.user.clientController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clients")
public class DeleteAllClientView {

    @GetMapping("/delete")
    public ModelAndView deleteAll(){
        ModelAndView modelAndView = new ModelAndView("reportClient");
        return modelAndView;
    }
}
