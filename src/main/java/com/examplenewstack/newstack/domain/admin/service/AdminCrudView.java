package com.examplenewstack.newstack.domain.admin.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/admins")
public class AdminCrudView {

    @GetMapping("/register")
    public String showRegisterAdminForm(){
        return "registerAdmin";
    }
}
