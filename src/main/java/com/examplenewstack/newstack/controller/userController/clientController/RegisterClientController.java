package com.examplenewstack.newstack.controller.userController.clientController;

import com.examplenewstack.newstack.model.dto.clientdto.ClientDTO;
import com.examplenewstack.newstack.model.usersinfo.client.Client;
import com.examplenewstack.newstack.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/client")
public class RegisterClientController {

    // Injeção de dependência do repositório de usuários
    private final ClientRepository clientRepository;
    // Construtor para injeção de dependência
    public RegisterClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/register")
    public ModelAndView registerScreen(){
        ModelAndView modelAndView = new ModelAndView("register");

        return modelAndView;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> register(@Valid @RequestBody ClientDTO clientDTO, BindingResult result){
        //verifica erros nos campos
        if(result.hasErrors()) {
            // Captura a mensagem de erro do CPF ou demais campos
            String errorMessage = result.getFieldError().getDefaultMessage();

            return ResponseEntity.badRequest().body(errorMessage);
        }

        Client client = clientDTO.toUser();
        // Salva os dados do usuário cadastrado
        clientRepository.save(client);

        return ResponseEntity.ok().build();
    }
}
