package com.examplenewstack.newstack.client.service;


import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.client.dto.ClientResponseDTO;
import com.examplenewstack.newstack.client.exception.NoCustomersFoundException;
import com.examplenewstack.newstack.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
