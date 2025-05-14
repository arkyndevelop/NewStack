package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.repository.ClientRepository;
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
            throw new CustomException("Erro: Nenhum cliente cadastrado!");
        }
        return clientRepository.findAll();

    }


}
