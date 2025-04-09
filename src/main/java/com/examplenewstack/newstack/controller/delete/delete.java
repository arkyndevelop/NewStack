package com.examplenewstack.newstack.controller.delete;


import com.examplenewstack.newstack.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class delete {

    private final ClientRepository clientRepository;

    public delete(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @DeleteMapping("/delete/{CPF}")
    @Transactional
    public RedirectView deleteByCPF(@PathVariable String CPF) {
        clientRepository.deleteByCPF(CPF);
        return  new RedirectView("/reports");
    }
}
