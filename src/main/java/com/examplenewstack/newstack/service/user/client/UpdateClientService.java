package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.dtos.client.ClientDTO;
import com.examplenewstack.newstack.entity.user.client.Client;
import com.examplenewstack.newstack.exceptions.client.CustomersSamePasswordException;
import com.examplenewstack.newstack.exceptions.employee.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.Optional;

@Service
public class UpdateClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public UpdateClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<Client> updateClient(@RequestBody ClientDTO clientDTO, @RequestParam Long id) {
        Optional<Client> clientExists = clientRepository.findById(id);

        if (clientExists.isPresent()) {
            Client client = clientRepository.getReferenceById(id);

            client.setName(clientDTO.getName());
            client.setCPF(clientDTO.getCPF());
            client.setEmail(clientDTO.getEmail());
            client.setTelephone(clientDTO.getTelephone());
            if (Objects.equals(clientDTO.getPassword(), clientDTO.getConfirmPassword())) {
                client.setPassword(clientDTO.getPassword());


            } else {
                throw  new CustomersSamePasswordException();
            }

            Client updateClients = clientRepository.save(client);
            return ResponseEntity.ok(updateClients);

        } else {
            throw  new NoEmployeersFoundByIdException();
        }

    }
}

