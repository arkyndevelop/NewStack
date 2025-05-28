package com.examplenewstack.newstack.client.service;


import com.examplenewstack.newstack.client.dto.ClientDTO;
import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.client.exception.CustomersRegisteredDataException;
import com.examplenewstack.newstack.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegisterClientService {

    @Autowired
    private final ClientRepository clientRepository;


    public RegisterClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Client registerClient(@RequestBody ClientDTO clientDTO) {


        if(clientRepository.existsByCPF(clientDTO.getCPF())){
            throw  new CustomersRegisteredDataException("cpf");
        }

        if (clientRepository.existsByEmail(clientDTO.getEmail())) {
            throw new CustomersRegisteredDataException("email");
        }

        if (clientRepository.existsByTelephone(clientDTO.getTelephone())) {
            throw new CustomersRegisteredDataException("telephone");
        }

        return clientRepository.save(clientDTO.toUser());
    }
}
