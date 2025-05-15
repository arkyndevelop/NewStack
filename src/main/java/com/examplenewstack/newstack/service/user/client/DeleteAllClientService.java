package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.entity.user.client.Client;
import com.examplenewstack.newstack.exceptions.client.NoCustomersFoundException;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
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
