package com.examplenewstack.newstack.domain.client.service;

import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.dto.ClientRequest;
import com.examplenewstack.newstack.domain.client.dto.ClientResponse;
import com.examplenewstack.newstack.domain.client.exception.CustomersRegisteredDataException;
import com.examplenewstack.newstack.domain.client.exception.NoCustomersFoundByIdException;
import com.examplenewstack.newstack.domain.client.exception.NoCustomersFoundException;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import com.examplenewstack.newstack.domain.employee.exception.NoEmployeersFoundByIdException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientCrudService {

    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClientCrudService(ClientRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Client registerClient(ClientRequest request){
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

    public List<ClientResponse> findAllClients() {
        List<Client> clientList = repository.findAll();

        if (clientList.isEmpty()) {
            throw new NoCustomersFoundException("Erro: Nenhum cliente cadastrado!");
        }

        return clientList
                .stream()
                .map( client -> new ClientResponse(
                        client.getId(),
                        client.getName(),
                        client.getCPF(),
                        client.getEmail(),
                        client.getTelephone(),
                        client.getDateRegister())) // Adicione a data de registro aqui
                .toList();
    }

    public ClientResponse showClientById(int id){
        Optional<Client> client = repository.findById(id);
        if (client.isEmpty()) {
            throw  new NoEmployeersFoundByIdException();
        }
        return new ClientResponse(
                client.get().getId(),
                client.get().getName(),
                client.get().getCPF(),
                client.get().getEmail(),
                client.get().getTelephone(),
                client.get().getDateRegister() // Adicione a data de registro aqui também
        );
    }

    public ResponseEntity<Client> updateClient(ClientRequest clientRequest, int id) {
        Optional<Client> clientExists = repository.findById(id);

        if (clientExists.isEmpty()) {
            throw new NoEmployeersFoundByIdException();
        }
        Client client = repository.getReferenceById(id);

        client.setName(clientRequest.name());
        client.setCPF(clientRequest.CPF());
        client.setEmail(clientRequest.email());
        client.setTelephone(clientRequest.telephone());


        Client updateClients = repository.save(client);
        return ResponseEntity.ok(updateClients);
    }

    public void deleteAllClients() {

        List<Client> existingClients = this.repository.findAll();
        if (existingClients.isEmpty()) {

            throw new NoCustomersFoundException("Erro: Nenhum cliente cadastrado!");
        }
        repository.deleteAll();
    }

    public ResponseEntity<Client> deleteByIdService(int id){
        Optional<Client> client = repository.findById(id);

        if (!client.isPresent()) {
            throw new NoCustomersFoundByIdException();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
