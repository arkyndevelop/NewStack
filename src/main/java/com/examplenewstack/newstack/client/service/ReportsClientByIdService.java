package com.examplenewstack.newstack.client.service;


import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.client.dto.ClientResponseDTO;
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

    public ClientResponseDTO showClientById(
            Long id
    ){
        Optional<Client> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw  new NoEmployeersFoundByIdException();
        }
        return new ClientResponseDTO(
                client.get().getId(),
                client.get().getName(),
                client.get().getCPF(),
                client.get().getEmail(),
                client.get().getTelephone()
        );
    }
}
