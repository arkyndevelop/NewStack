package com.examplenewstack.newstack.client.service;


import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.employee.exception.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class ReportsClientByIdService {

    private final ClientRepository clientRepository;

    public ReportsClientByIdService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> showClientById(
            Long id
    ){
        Optional<Client> clientList = clientRepository.findById(id);

        if (clientList.isEmpty()) {
            throw  new NoEmployeersFoundByIdException();
        }
        return clientRepository.findById(id);
    }
}
