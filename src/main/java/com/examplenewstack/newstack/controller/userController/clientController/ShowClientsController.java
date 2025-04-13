package com.examplenewstack.newstack.controller.userController.clientController;

import com.examplenewstack.newstack.model.usersinfo.client.Client;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ShowClientsController {

    private final ClientRepository clientRepository;

    public ShowClientsController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/reports")
    public ModelAndView reports(){
        List<Client> clientList = this.clientRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("reports");
        modelAndView.addObject("clientList", clientList);
        return modelAndView;
    }
}
