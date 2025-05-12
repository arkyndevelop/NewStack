package com.examplenewstack.newstack.controller.user.clientController;

import com.examplenewstack.newstack.controller.user.clientController.service.UpdateClient;
import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class UpdateClientController {

    private final UpdateClient updateServices;

    public UpdateClientController(UpdateClient updateServices) {
        this.updateServices = updateServices;
    }

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