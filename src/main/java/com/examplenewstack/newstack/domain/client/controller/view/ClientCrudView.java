package com.examplenewstack.newstack.domain.client.controller.view;

import com.examplenewstack.newstack.core.entity.User;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.dto.ClientRequest;
import com.examplenewstack.newstack.domain.client.dto.ClientResponse;
import com.examplenewstack.newstack.domain.client.dto.ClientResponseProfileDetails;
import com.examplenewstack.newstack.domain.client.service.ClientCrudService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/v1/clients")
public class ClientCrudView {

    private final ClientCrudService service;

    public ClientCrudView(ClientCrudService service) {
        this.service = service;
    }

    // Página de cadastro
    @GetMapping("/register")
    public ModelAndView clientRegisterView() {
        return new ModelAndView("registerClient");
    }

    // Relatório de todos os clientes
    @GetMapping("/report")
    public ModelAndView showClientById() {
        List<ClientResponse> clientList = service.findAllClients();
        ModelAndView modelAndView = new ModelAndView("reportClient");
        modelAndView.addObject("clientList", clientList);
        return modelAndView;
    }

    // Exibe perfil do cliente logado
    @GetMapping("/profile")
    public ModelAndView showMyProfile(@RequestParam(value = "message", required = false) String message,
                                      @RequestParam(value = "error", required = false) String error) {
        ModelAndView mav = new ModelAndView("profileClient");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            mav.addObject("client", principal);
        }

        if (message != null) {
            mav.addObject("message", message);
        }

        if (error != null) {
            mav.addObject("error", error);
        }

        return mav;
    }

    // Atualiza o perfil do cliente
    @PostMapping("/profile")
    public ModelAndView updateClientProfile(@ModelAttribute("client") Client updatedClient) {
        ModelAndView mav = new ModelAndView("profileClient");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        try {
            if (principal instanceof Client) {
                Client currentClient = (Client) principal;
                currentClient.setName(updatedClient.getName());
                currentClient.setEmail(updatedClient.getEmail());
                currentClient.setTelephone(updatedClient.getTelephone());

                if (updatedClient.getAddress() != null) {
                    if (currentClient.getAddress() == null) {
                        currentClient.setAddress(new com.examplenewstack.newstack.domain.address.Address());
                    }
                    currentClient.getAddress().setStreet(updatedClient.getAddress().getStreet());
                    currentClient.getAddress().setNumber_house(updatedClient.getAddress().getNumber_house());
                    currentClient.getAddress().setNeighborhood(updatedClient.getAddress().getNeighborhood());
                    currentClient.getAddress().setCep(updatedClient.getAddress().getCep());
                    currentClient.getAddress().setCity(updatedClient.getAddress().getCity());
                    currentClient.getAddress().setState(updatedClient.getAddress().getState());
                    currentClient.getAddress().setComplement(updatedClient.getAddress().getComplement());
                }

                ClientRequest req = new ClientRequest(
                        currentClient.getName(),
                        currentClient.getCPF(),
                        currentClient.getEmail(),
                        currentClient.getTelephone(),
                        currentClient.getPassword()
                );


                //Metodo para usar para ver perfil de client com detalhes
                ClientResponseProfileDetails reqDetails = new ClientResponseProfileDetails(
                        currentClient.getName(),
                        currentClient.getCPF(),
                        currentClient.getEmail(),
                        currentClient.getTelephone(),
                        currentClient.getDateRegister(),
                        currentClient.getAddress()
                );

                service.updateClient(req, currentClient.getId());
                mav.addObject("client", currentClient);
                mav.addObject("message", "Cliente atualizado com sucesso!");
            } else {
                mav.addObject("error", "Usuário não autenticado.");
            }
        } catch (Exception e) {
            mav.addObject("client", principal);
            mav.addObject("error", "Erro ao atualizar cliente: " + e.getMessage());
        }

        return mav;
    }

    // (Apenas View placeholders) - Remover todos os clientes
    @GetMapping("/delete/all")
    public ModelAndView deleteAll() {
        return new ModelAndView("reportClient");
    }

    // (Apenas View placeholders) - Remover cliente por ID
    @GetMapping("/deleteById")
    public ModelAndView deleteById() {
        return new ModelAndView("reportClient");
    }
}
