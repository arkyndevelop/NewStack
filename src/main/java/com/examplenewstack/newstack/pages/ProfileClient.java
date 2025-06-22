package com.examplenewstack.newstack.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileClient {

    @GetMapping("/clients/profiler")
    public ModelAndView clientsProfiler(){
        return new ModelAndView("profileClient");
    }
}
