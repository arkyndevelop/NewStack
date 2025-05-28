package com.examplenewstack.newstack.client.service;


import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.client.exception.NoCustomersFoundException;
import com.examplenewstack.newstack.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
