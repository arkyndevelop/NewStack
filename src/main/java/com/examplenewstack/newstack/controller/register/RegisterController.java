package com.examplenewstack.newstack.controller.register;

import com.examplenewstack.newstack.dto.User;
import com.examplenewstack.newstack.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//import com.examplenewstack.newstack.model.user.User;

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
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result){
        //verifica erros nos campos

        if(result.hasErrors()) {
            // Captura a mensagem de erro do CPF ou demais campos
            String errorMessage = result.getFieldError().getDefaultMessage();

            return ResponseEntity.badRequest().body(errorMessage);
        }

        com.examplenewstack.newstack.model.user.User user1 = user.toUser();



        // Persistencia

        userRepository.save(user1);

        return  ResponseEntity.ok().build();

    }

}
