package com.examplenewstack.newstack.domain.client.service;


import com.examplenewstack.newstack.domain.client.dto.ClientRequestDTO;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.employee.exception.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateClientService {

    private final ClientRepository clientRepository;

    public UpdateClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<Client> updateClient(
            ClientRequestDTO clientRequestDTO,
            int id
    ) {
        Optional<Client> clientExists = clientRepository.findById(id);

        if (clientExists.isEmpty()) {
            throw new NoEmployeersFoundByIdException();
        }
        Client client = clientRepository.getReferenceById(id);

        client.setName(clientRequestDTO.name());
        client.setCPF(clientRequestDTO.CPF());
        client.setEmail(clientRequestDTO.email());
        client.setTelephone(clientRequestDTO.telephone());


        Client updateClients = clientRepository.save(client);
        return ResponseEntity.ok(updateClients);
    }
}

