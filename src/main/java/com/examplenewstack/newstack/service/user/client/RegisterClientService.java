package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.entity.dto.clientdto.ClientDTO;
import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import com.examplenewstack.newstack.exception.ClientsException.RegisteredDataException;
import com.examplenewstack.newstack.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegisterClientService {

    private final ClientRepository clientRepository;


    public RegisterClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Client registerClient(@Valid @RequestBody ClientDTO clientDTO) {


        if(clientRepository.existsByCPF(clientDTO.getCPF())){
            throw  new RegisteredDataException("Erro: CPF ja cadastrado!");
        }

        if (clientRepository.existsByEmail(clientDTO.getEmail())) {
            throw new RegisteredDataException("Erro: Email ja cadastrado!");
        }

        if (clientRepository.existsByTelephone(clientDTO.getTelephone())) {
            throw new RegisteredDataException("Erro: Telefone ja cadastrado!");
        }

        return clientRepository.save(clientDTO.toUser());
    }
}
