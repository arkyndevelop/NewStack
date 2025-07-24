package com.examplenewstack.newstack.domain.client.view;

import com.examplenewstack.newstack.domain.client.dto.ClientProfileUpdateRequest;
import com.examplenewstack.newstack.domain.client.dto.ClientRequest;
import com.examplenewstack.newstack.domain.client.dto.ClientResponse;
import com.examplenewstack.newstack.domain.client.dto.ClientResponseProfileDetails;
import com.examplenewstack.newstack.domain.client.exception.ClientsRegisteredDataException;
import com.examplenewstack.newstack.domain.client.exception.NoCustomersFoundByIdException;
import com.examplenewstack.newstack.domain.client.service.ClientCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/v1/clients")
public class ClientCrudView {

    private final ClientCrudService clientService;

    @Autowired
    public ClientCrudView(ClientCrudService clientService) {
        this.clientService = clientService;
    }


    // Exibe a página de cadastro de cliente.
    @GetMapping("/register")
    public ModelAndView showRegisterView() {
        // Apenas retorna a view do formulário de cadastro.
        return new ModelAndView("registerClient");
    }


    // Processa a submissão do formulário de registro de um novo cliente.
    @PostMapping("/register")
    public String handleRegistration(@ModelAttribute ClientRequest clientRequest, RedirectAttributes redirectAttributes) {
        try {
            clientService.registerClient(clientRequest);
            // Em caso de sucesso, envia uma mensagem para a página de login.
            redirectAttributes.addFlashAttribute("message", "Cadastro realizado com sucesso! Faça o login.");
            return "redirect:/v1/login";
        } catch (ClientsRegisteredDataException e) {
            // Em caso de erro (ex: CPF duplicado), devolve para a pág. de registro com a mensagem de erro.
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/v1/clients/register";
        }
    }


    // Exibe a página de relatório com todos os clientes.
    @GetMapping("/report")
    public ModelAndView showClientsReport() {
        List<ClientResponse> clientList = clientService.findAllClients();
        ModelAndView mav = new ModelAndView("reportClient");
        mav.addObject("clientList", clientList);
        return mav;
    }


    // Exibe a página de perfil do cliente que está logado.
    @GetMapping("/profile")
    public ModelAndView showMyProfile() {
        ClientResponseProfileDetails clientDetails = clientService.getAuthenticatedClientProfile();
        ModelAndView mav = new ModelAndView("profileClient");
        mav.addObject("client", clientDetails);
        return mav;
    }

    // Processa a atualização do próprio perfil pelo cliente logado.
    // Utiliza o DTO seguro 'ClientProfileUpdateRequest'.
    @PostMapping("/profile/update")
    public String handleProfileUpdate(@ModelAttribute ClientProfileUpdateRequest profileRequest, RedirectAttributes redirectAttributes) {
        try {
            clientService.updateAuthenticatedClientProfile(profileRequest);
            redirectAttributes.addFlashAttribute("message", "Perfil atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao atualizar perfil: " + e.getMessage());
        }
        return "redirect:/v1/clients/profile";
    }


    // Exibe a página de perfil de um cliente específico pelo ID (para uso administrativo).
    @GetMapping("/profile/{id}")
    public ModelAndView showClientProfileById(@PathVariable("id") int id) {
        ClientResponseProfileDetails clientDetails = clientService.getClientProfileById(id);
        ModelAndView mav = new ModelAndView("profileClient");
        mav.addObject("client", clientDetails);
        return mav;
    }

    // Exibe o formulário de edição de um cliente (para uso administrativo).
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") int id) {
        ClientResponseProfileDetails clientDetails = clientService.getClientProfileById(id);
        ModelAndView mav = new ModelAndView("editClient");
        mav.addObject("client", clientDetails);
        return mav;
    }


     // Processa a atualização de um cliente feita por um administrador.
     @PostMapping("/edit/{id}")
     public String handleAdminUpdate(@PathVariable("id") int id,
                                     @ModelAttribute ClientProfileUpdateRequest profileRequest,
                                     RedirectAttributes redirectAttributes) {
         try {
             // Agora chamamos o novo metodo de serviço
             clientService.updateClientByAdmin(id, profileRequest);
             redirectAttributes.addFlashAttribute("message", "Cliente atualizado com sucesso!");
         } catch (Exception e) {
             redirectAttributes.addFlashAttribute("error", "Erro ao atualizar cliente: " + e.getMessage());
         }
         return "redirect:/v1/clients/report";
     }

    @PostMapping("/delete/{id}") // Ou @DeleteMapping, ambos funcionam com o filtro
    public String handleDeleteClient(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            clientService.deleteClientById(id);
            redirectAttributes.addFlashAttribute("message", "Cliente excluído com sucesso!");
        } catch (NoCustomersFoundByIdException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocorreu um erro inesperado ao tentar excluir o cliente.");
        }
        return "redirect:/v1/clients/report";
    }
}