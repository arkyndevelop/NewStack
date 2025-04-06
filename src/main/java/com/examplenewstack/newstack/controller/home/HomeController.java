package com.examplenewstack.newstack.controller.home;

// Importações necessárias para o funcionamento do Controller
import com.examplenewstack.newstack.model.usersinfo.client.Client;
import com.examplenewstack.newstack.repository.ClientRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller// Define que esta classe é um Controller Spring
@RequestMapping("/home")// Mapeia todas as rotas deste controller para '/home'
public class HomeController {

    // Injeção de dependência do repositório de usuários
    private final ClientRepository clientRepository;
    // Construtor para injeção de dependência
    public HomeController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Manipula requisições GET para '/home/login'
    @GetMapping("/login")
    public ModelAndView loginScreen(HttpServletRequest request , Model model){
        // Cria e retorna uma view chamada 'login'
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
    // Manipula requisições POST para '/home/login'
    @PostMapping("/login")
    public String login(
            @RequestParam String cpf,
            @RequestParam String password,
            HttpSession session,
            Model model
    ){
        // Busca usuário no banco de dados por CPF e senha
        Client client = clientRepository.findByCPFAndPassword(cpf,password);

        if(client != null){
            // Armazena o usuário na sessão com chave "LoginFeito"
            session.setAttribute("LoginFeito", client);
            // Redireciona para a página inicial
            return "redirect:/home/index";
        }else{
            // Adiciona mensagem de erro ao modelo
            model.addAttribute("error" , "CPF e/ou Senha inválidos!");

            return "login";
        }
    }
}