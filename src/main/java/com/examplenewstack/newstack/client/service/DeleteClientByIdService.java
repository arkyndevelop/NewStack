package com.examplenewstack.newstack.client.service;

import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.client.exception.NoCustomersFoundByIdException;
import com.examplenewstack.newstack.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class DeleteClientByIdService {

    @Autowired
    private final ClientRepository clientRepository;


    public DeleteClientByIdService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<Client> deleteById(@RequestParam Long id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            clientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        throw new NoCustomersFoundByIdException();

    }
}
