package com.examplenewstack.newstack.client.service;


import com.examplenewstack.newstack.client.dto.ClientRequestDTO;
import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.client.exception.CustomersRegisteredDataException;
import com.examplenewstack.newstack.client.repository.ClientRepository;
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
            throw new CustomersRegisteredDataException("cpf");
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
