package com.examplenewstack.newstack.controller.update;

import com.examplenewstack.newstack.model.usersinfo.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private UpdateServices updateServices;

    @PostMapping("/update/{cpf}")
    public String updateClient(@PathVariable String cpf, @ModelAttribute Client client) {
        try {
            updateServices.updateClient(cpf, client);
            return "redirect:/reports";
        } catch (Exception e) {
            return "redirect:/reports" + e.getMessage().replace(" ", "+");
        }
    }
}