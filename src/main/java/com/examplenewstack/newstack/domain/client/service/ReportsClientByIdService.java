package com.examplenewstack.newstack.domain.client.service;


import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.dto.ClientResponseDTO;
import com.examplenewstack.newstack.domain.employee.exception.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportsClientByIdService {

    private final ClientRepository clientRepository;

    public ReportsClientByIdService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponseDTO showClientById(
            int id
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
