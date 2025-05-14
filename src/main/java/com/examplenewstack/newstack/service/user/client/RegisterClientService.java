package com.examplenewstack.newstack.service.user.client;


import com.examplenewstack.newstack.entity.dto.clientdto.ClientDTO;
import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.repository.ClientRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class RegisterClientService {

    private final ClientRepository clientRepository;


    public RegisterClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Client registerClient(@Valid @RequestBody ClientDTO clientDTO) {



        return clientRepository.save(clientDTO.toUser());
    }
}
