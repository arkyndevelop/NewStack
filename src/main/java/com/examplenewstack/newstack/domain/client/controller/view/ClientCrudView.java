package com.examplenewstack.newstack.domain.client.controller.view;


import com.examplenewstack.newstack.core.entity.User;
import com.examplenewstack.newstack.domain.client.dto.ClientResponseProfile;
import com.examplenewstack.newstack.domain.client.service.ClientCrudService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/v1/clients")
public class ClientCrudView {

    private final ClientCrudService service;

    public ClientCrudView(ClientCrudService service) {
        this.service = service;
    }

    @GetMapping("/register")
    public ModelAndView clientRegisterView(){
        return new ModelAndView("registerClient");
    }

    @GetMapping("/report")
    public ModelAndView showClientById(){
        // 1. Busca a lista de clientes do serviço
        List<ClientResponseProfile> clientList = service.findAllClients();

        // 2. Cria o objeto ModelAndView, apontando para o arquivo HTML
        ModelAndView modelAndView = new ModelAndView("reportClient");

        // 3. Adiciona a lista de clientes ao modelo com o nome "clientList"
        modelAndView.addObject("clientList", clientList);

        // 4. Retorna o modelo com os dados para a view
        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView showMyProfile() {
        ModelAndView mav = new ModelAndView("profileClient");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        // Passa o objeto do usuário logado para a página de perfil
        if (principal instanceof User) {
            mav.addObject("client", principal);
        }
        return mav;
    }

    @GetMapping("/delete/all")
    public ModelAndView deleteAll(){
        return new ModelAndView("reportClient");
    }

    @GetMapping("/deleteById")
    public ModelAndView deleteById(){
        return new ModelAndView("reportClient");
    }
}
