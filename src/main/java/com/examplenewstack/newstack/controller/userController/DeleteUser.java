package com.examplenewstack.newstack.controller.userController;


import com.examplenewstack.newstack.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class DeleteUser {

    private final ClientRepository clientRepository;

    public DeleteUser(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @PostMapping("/delete/{CPF}")
    @Transactional
    public RedirectView deleteByCPF(@PathVariable String CPF) {
        clientRepository.deleteByCPF(CPF);
        return  new RedirectView("/reports");
    }
}
