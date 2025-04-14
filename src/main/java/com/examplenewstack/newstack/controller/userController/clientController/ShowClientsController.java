package com.examplenewstack.newstack.controller.userController.clientController;

import com.examplenewstack.newstack.model.usersinfo.client.Client;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
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

        // Organiza a Lista antes de exibi-la
        // Observação: Esse metodo pega nome por nome, realizando a comparação para depois organizar
        clientList.sort(Comparator.comparing(Client::getName));

        ModelAndView modelAndView = new ModelAndView("reportClient");
        modelAndView.addObject("clientList", clientList);
        return modelAndView;
    }
}
