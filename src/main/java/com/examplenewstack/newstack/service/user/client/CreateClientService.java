package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.entity.dto.clientdto.ClientDTO;
import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CreateClientService {

    private final ClientRepository clientRepository;


    public CreateClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Client createClient(@RequestBody ClientDTO clientDTO) {

        if (clientRepository.findByCPF(clientDTO.getCPF()) || clientRepository.findByEmail(clientDTO.getEmail())) {
            throw new CustomException("Dados de usuario ja cadastrados!");

        }
        return clientRepository.save(clientDTO.toUser());
    }
}
