package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import com.examplenewstack.newstack.exception.ClientsException.CustomException;
import com.examplenewstack.newstack.exception.ClientsException.NoCustomersFoundException;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class ReportsClientByIdService {

    private final ClientRepository clientRepository;

    public ReportsClientByIdService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Optional<Client> showClientById(@PathVariable Long id) {

        Optional<Client> clientList = clientRepository.findById(id);

        if (clientList.isEmpty()) {
            throw new NoCustomersFoundException("Erro: Nenhum cliente cadastrado com esse id!");
        }
        return clientRepository.findById(id);

    }
}
