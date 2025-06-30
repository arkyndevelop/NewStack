package com.examplenewstack.newstack.domain.client.service;


import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.dto.ClientRequestDTO;
import com.examplenewstack.newstack.domain.client.exception.CustomersRegisteredDataException;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterClientService {

    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;


    public RegisterClientService(ClientRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
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

        Client newClient = request.toClient();

        String encodedPassword = passwordEncoder.encode(request.password());
        newClient.setPassword(encodedPassword);

        return repository.save(newClient);
    }
}
