package com.examplenewstack.newstack.client.service;


import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.client.exception.NoCustomersFoundException;
import com.examplenewstack.newstack.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsAllClientService {

    private final ClientRepository clientRepository;

    public ReportsAllClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAllClients() {
        List<Client> clientList = clientRepository.findAll();

        if (clientList.isEmpty()) {
            throw new NoCustomersFoundException("Erro: Nenhum cliente cadastrado!");
        }
        return clientRepository.findAll();
    }
}
