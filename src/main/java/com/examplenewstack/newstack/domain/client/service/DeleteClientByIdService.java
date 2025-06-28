package com.examplenewstack.newstack.domain.client.service;

import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.exception.NoCustomersFoundByIdException;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteClientByIdService {

    private final ClientRepository clientRepository;

    public DeleteClientByIdService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<Client> deleteById(
            int id
    ){
        Optional<Client> client = clientRepository.findById(id);

        if (!client.isPresent()) {
            throw new NoCustomersFoundByIdException();
        }
        clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
