package com.examplenewstack.newstack.domain.client.view;

import com.examplenewstack.newstack.domain.client.dto.ClientResponse;
import com.examplenewstack.newstack.domain.client.service.ClientCrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ShowAllClientView {

    private final ClientCrudService clientCrudService;

    public ShowAllClientView(ClientCrudService clientCrudService) {
        this.clientCrudService = clientCrudService;
    }


    @GetMapping("/report/all")
    public ModelAndView showAllClients(){
        // 1. Busca a lista de clientes do serviço
        List<ClientResponse> clientList = clientCrudService.findAllClients();

        // 2. Cria o objeto ModelAndView, apontando para o arquivo HTML
        ModelAndView modelAndView = new ModelAndView("reportClient");

        // 3. Adiciona a lista de clientes ao modelo com o nome "clientList"
        modelAndView.addObject("clientList", clientList);

        // 4. Retorna o modelo com os dados para a view
        return modelAndView;
    }
}