package com.examplenewstack.newstack.client.service;


import com.examplenewstack.newstack.client.dto.ClientRequestDTO;
import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.client.exception.CustomersRegisteredDataException;
import com.examplenewstack.newstack.client.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegisterClientService {

    private final ClientRepository clientRepository;

    public RegisterClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client registerClient(
            ClientRequestDTO clientRequestDTO
    ) {

        if (clientRepository.existsByCPF(clientRequestDTO.CPF())) {
            throw new CustomersRegisteredDataException("cpf");
        }
        if (clientRepository.existsByEmail(clientRequestDTO.email())) {
            throw new CustomersRegisteredDataException("email");
        }
        if (clientRepository.existsByTelephone(clientRequestDTO.telephone())) {
            throw new CustomersRegisteredDataException("telephone");
        }

        return clientRepository.save(clientRequestDTO.toUser());
    }
}
