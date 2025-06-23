package com.examplenewstack.newstack.domain.client.service;


import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.exception.NoCustomersFoundException;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteAllClientService {

    private final ClientRepository clientRepository;

    public DeleteAllClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void deleteAllClients() {

        List<Client> existingClients = this.clientRepository.findAll();
        if (existingClients.isEmpty()) {

            throw new NoCustomersFoundException("Erro: Nenhum cliente cadastrado!");
        }
        clientRepository.deleteAll();
    }
}
