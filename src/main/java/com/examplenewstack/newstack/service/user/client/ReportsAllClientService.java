package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.entity.user.client.Client;
import com.examplenewstack.newstack.exceptions.client.NoCustomersFoundException;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsAllClientService {

    @Autowired
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
