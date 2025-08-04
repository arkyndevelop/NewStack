package com.examplenewstack.newstack.domain.client.view;

import com.examplenewstack.newstack.domain.client.dto.ClientResponse;
import com.examplenewstack.newstack.domain.client.service.ClientCrudService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView showAllClients(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        
        try {
            Page<ClientResponse> clientPage = clientCrudService.getFilteredClients(page, size, orderBy, direction);
            ModelAndView modelAndView = new ModelAndView("reportClient");
            modelAndView.addObject("clientPage", clientPage);
            modelAndView.addObject("clientList", clientPage.getContent());
            
            // Adicionar informações de paginação para o template
            modelAndView.addObject("currentPage", page);
            modelAndView.addObject("totalPages", clientPage.getTotalPages());
            modelAndView.addObject("totalElements", clientPage.getTotalElements());
            modelAndView.addObject("size", size);
            modelAndView.addObject("orderBy", orderBy);
            modelAndView.addObject("direction", direction);
            
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("reportClient");
            modelAndView.addObject("clientList", List.of());
            modelAndView.addObject("clientPage", null);
            return modelAndView;
        }
    }
}