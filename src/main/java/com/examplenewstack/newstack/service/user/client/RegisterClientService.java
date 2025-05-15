package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.dtos.client.ClientDTO;
import com.examplenewstack.newstack.entity.user.client.Client;
import com.examplenewstack.newstack.exceptions.client.ClientsRegisteredDataException;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegisterClientService {

    private final ClientRepository clientRepository;


    public RegisterClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Client registerClient(@RequestBody ClientDTO clientDTO) {


        if(clientRepository.existsByCPF(clientDTO.getCPF())){
            throw  new ClientsRegisteredDataException("cpf");
        }

        if (clientRepository.existsByEmail(clientDTO.getEmail())) {
            throw new ClientsRegisteredDataException("email");
        }

        if (clientRepository.existsByTelephone(clientDTO.getTelephone())) {
            throw new ClientsRegisteredDataException("telephone");
        }

        return clientRepository.save(clientDTO.toUser());
    }
}
