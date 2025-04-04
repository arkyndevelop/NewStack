package com.examplenewstack.newstack.controller.register;

import com.examplenewstack.newstack.model.dto.usersdto.UserDTO;
import com.examplenewstack.newstack.model.usersinfo.User;
import com.examplenewstack.newstack.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/crud")
public class RegisterController {
    private final UserRepository userRepository;


    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public ModelAndView registerScreen(HttpServletRequest request, Model model){
        ModelAndView modelAndView = new ModelAndView("register");

        return modelAndView;
    }

    @PostMapping("/register/new")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult result){
        //verifica erros nos campos
        if(result.hasErrors()) {
            // Captura a mensagem de erro do CPF ou demais campos
            String errorMessage = result.getFieldError().getDefaultMessage();

            return ResponseEntity.badRequest().body(errorMessage);
        }

        User user = userDTO.toUser();

        // Salva os dados do usuário cadastrado
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
