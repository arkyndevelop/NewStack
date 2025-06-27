package com.examplenewstack.newstack.domain.client.service;


import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.dto.ClientRequestDTO;
import com.examplenewstack.newstack.domain.client.exception.CustomersRegisteredDataException;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterClientService {

    private final ClientRepository repository;


    public RegisterClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client registerClient(
            ClientRequestDTO request
    ){
        if (repository.existsByCPF(request.CPF())) {
            throw new CustomersRegisteredDataException("CPF");
        }
        if (repository.existsByEmail(request.email())) {
            throw new CustomersRegisteredDataException("email");
        }
        if (repository.existsByTelephone(request.telephone())) {
            throw new CustomersRegisteredDataException("telephone");
        }
        return repository.save(request.toClient());
    }
}
