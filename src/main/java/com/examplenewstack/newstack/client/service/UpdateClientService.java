package com.examplenewstack.newstack.client.service;


import com.examplenewstack.newstack.client.dto.ClientRequestDTO;
import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.client.dto.ClientResponseDTO;
import com.examplenewstack.newstack.client.exception.CustomersSamePasswordException;
import com.examplenewstack.newstack.employee.exception.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
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

