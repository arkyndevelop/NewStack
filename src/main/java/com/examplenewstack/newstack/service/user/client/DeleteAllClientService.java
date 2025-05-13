package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteAllClientService {

    private final ClientRepository clientRepository;

    public DeleteAllClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void deleteAllClients() {

        List<Client> existingClients = this.clientRepository.findAll();

        try {
            if (existingClients.isEmpty()) {

                throw new CustomException("Erro ao encontrar clientes");
            }


                clientRepository.deleteAll();
            ResponseEntity.ok().build();


        } catch (Exception e ){

            ResponseEntity.badRequest().build();
        }



    }



}
