package com.examplenewstack.newstack.controllers.auth;

import com.examplenewstack.newstack.admin.AdminMaster;
import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.admin.repository.AdminRepository;
import com.examplenewstack.newstack.client.repository.ClientRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/v1")
public class LoginController {

    // Injeção de dependência do repositório de usuários
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    // Construtor para injeção de dependência
    public LoginController(ClientRepository clientRepository, AdminRepository adminRepository) {
        this.clientRepository = clientRepository;
        this.adminRepository = adminRepository;
    }

    // Manipula requisições GET para '/home/login'
    @GetMapping("/login")
    public ModelAndView loginScreen(HttpServletRequest request , Model model){
        // Cria e retorna uma view chamada 'login'
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    // Manipula requisições POST para '/home/login'
    @PostMapping("/auth")
    public String login(
            @RequestParam String cpf,
            @RequestParam String password,
            HttpSession session,
            Model model
    ){
        cpf = cpf.replaceAll("[^0-9]", "");

        // Busca usuário no banco de dados por CPF e senha
        Client client = clientRepository.findByCPFAndPassword(cpf,password);
        AdminMaster adminMaster = adminRepository.findByCPFAndPassword(cpf, password);

        if(client != null){
            // Armazena o usuário na sessão com chave "LoginFeito"
            session.setAttribute("LoginFeito", true);
            session.setAttribute("tipoUsuario", client);
            session.setAttribute("usuarioID", client.getId());
            // Redireciona para a página inicial
            // Alterar o redirecionamento o mais rápido possível para uma home apenas de clientes
            return "redirect:/v1/home";
        }
        else if(adminMaster != null){
            session.setAttribute("LoginFeito", true);
            session.setAttribute("tipoUsuario", adminMaster);
            session.setAttribute("usuarioID", adminMaster.getId());
            // Redireciona para a página inicial
            return "redirect:/v1/home";
        }
        else {
            // Adiciona mensagem de erro ao modelo
            model.addAttribute("error" , "CPF e/ou Senha inválidos!");

            return "login";
        }
    }
}