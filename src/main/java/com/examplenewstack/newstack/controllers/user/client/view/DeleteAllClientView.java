package com.examplenewstack.newstack.controllers.user.client.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clients")
public class DeleteAllClientView {

    @GetMapping("/delete/all")
    public ModelAndView deleteAll(){
        ModelAndView modelAndView = new ModelAndView("reportClient");
        return modelAndView;
    }
}
