package com.examplenewstack.newstack.controller.userController.clientController.service;


import com.examplenewstack.newstack.model.usersinfo.client.Client;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateClient {

    private final ClientRepository clientRepository;

    public UpdateClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void updateClient(String cpf, Client clientUpdate){
        Optional<Client> clienteOptional = Optional.ofNullable(clientRepository.findByCPF(cpf));

        if(clienteOptional.isPresent()){
            Client clientExist = clienteOptional.get();

            if(clientUpdate.getName() != null){
                clientExist.setName(clientUpdate.getName());
            }
            if(clientUpdate.getCPF() != null){
                clientExist.setCPF(clientUpdate.getCPF());
            }
            if(clientUpdate.getEmail() != null){
                clientExist.setEmail(clientUpdate.getEmail());
            }
            if(clientUpdate.getTelephone() != null){
                clientExist.setTelephone(clientUpdate.getTelephone());
            }
            if(clientUpdate.getPassword() != null){
                clientExist.setPassword(clientUpdate.getPassword());
            }
            clientRepository.save(clientExist);
        } else {
            throw new RuntimeException("Cliente não encontrado pelo CPF " + cpf);
        }
    }
}





