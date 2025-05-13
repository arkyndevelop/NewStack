package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowAllClientService {

    private final ClientRepository clientRepository;


    public ShowAllClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAllClients() {

        List<Client> existing = clientRepository.findAll();

        if (existing.isEmpty()) {
            throw new CustomException("Erro: Nenhum cliente cadastrado!");
        }
        return clientRepository.findAll();

    }


}
