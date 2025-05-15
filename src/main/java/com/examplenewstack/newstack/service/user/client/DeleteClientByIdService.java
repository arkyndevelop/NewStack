package com.examplenewstack.newstack.service.user.client;

import com.examplenewstack.newstack.entity.user.client.Client;
import com.examplenewstack.newstack.exceptions.client.NoCustomersFoundException;
import com.examplenewstack.newstack.exceptions.employee.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class DeleteClientByIdService {

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

        throw  new NoEmployeersFoundByIdException();


    }
}
