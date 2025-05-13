package com.examplenewstack.newstack.controller.user.clientController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clients")
public class DeleteByIdView {

    @DeleteMapping("/deleteBy/{id}")
    public ModelAndView deleteById(){
        ModelAndView modelAndView = new ModelAndView("reportClient");
        return modelAndView;

    }
}
