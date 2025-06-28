package com.examplenewstack.newstack.domain.client.service;


import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.dto.ClientResponseDTO;
import com.examplenewstack.newstack.domain.client.exception.NoCustomersFoundException;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsAllClientService {

    private final ClientRepository clientRepository;

    public ReportsAllClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientResponseDTO> findAllClients() {
        List<Client> clientList = clientRepository.findAll();

        if (clientList.isEmpty()) {
            throw new NoCustomersFoundException("Erro: Nenhum cliente cadastrado!");
        }

        return clientList
                .stream()
                .map( client -> new ClientResponseDTO(
                        client.getId(),
                        client.getName(),
                        client.getCPF(),
                        client.getEmail(),
                        client.getTelephone()))
                .toList();
    }
}
