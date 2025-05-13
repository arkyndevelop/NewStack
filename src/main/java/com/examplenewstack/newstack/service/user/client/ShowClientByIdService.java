package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ShowClientByIdService {

    private final ClientRepository clientRepository;

    public ShowClientByIdService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Optional<Client> showClientById(@PathVariable Long id) {

        Optional<Client> clientList = clientRepository.findById(id);

        if (clientList.isEmpty()) {
            throw new CustomException("Erro: Nenhum cliente cadastrado");
        }
        return clientRepository.findById(id);

    }
}
