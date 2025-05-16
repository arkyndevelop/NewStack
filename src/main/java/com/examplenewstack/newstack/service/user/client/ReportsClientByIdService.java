package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.entity.user.client.Client;
import com.examplenewstack.newstack.exceptions.client.NoCustomersFoundException;
import com.examplenewstack.newstack.exceptions.employee.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class ReportsClientByIdService {

    @Autowired
    private final ClientRepository clientRepository;

    public ReportsClientByIdService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Optional<Client> showClientById(@PathVariable Long id) {

        Optional<Client> clientList = clientRepository.findById(id);

        if (clientList.isEmpty()) {
            throw  new NoEmployeersFoundByIdException();
        }
        return clientRepository.findById(id);

    }
}
