package com.examplenewstack.newstack.domain.client.service;

import com.examplenewstack.newstack.domain.address.Address;
import com.examplenewstack.newstack.domain.address.repository.AddressRepository;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.dto.ClientProfileUpdateRequest;
import com.examplenewstack.newstack.domain.client.dto.ClientRequest;
import com.examplenewstack.newstack.domain.client.dto.ClientResponse;
import com.examplenewstack.newstack.domain.client.dto.ClientResponseProfileDetails;
import com.examplenewstack.newstack.domain.client.exception.ClientsRegisteredDataException;
import com.examplenewstack.newstack.domain.client.exception.CustomersRegisteredDataException;
import com.examplenewstack.newstack.domain.client.exception.NoCustomersFoundByIdException;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import com.examplenewstack.newstack.domain.loan.Loan;
import com.examplenewstack.newstack.domain.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientCrudService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final LoanRepository loanRepository;

    public ClientCrudService(ClientRepository clientRepository, PasswordEncoder passwordEncoder, AddressRepository addressRepository, LoanRepository loanRepository) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
        this.loanRepository = loanRepository;
    }

    public Client registerClient(ClientRequest request){
        if (clientRepository.existsByCPF(request.CPF())) {
            throw new ClientsRegisteredDataException("cpf");
        }
        if (clientRepository.existsByEmail(request.email())) {
            throw new CustomersRegisteredDataException("email");
        }
        if (clientRepository.existsByTelephone(request.telephone())) {
            throw new CustomersRegisteredDataException("telephone");
        }

        Client newClient = request.toClient();

        String encodedPassword = passwordEncoder.encode(request.password());
        newClient.setPassword(encodedPassword);

        return clientRepository.save(newClient);
    }

    @Transactional(readOnly = true)
    public List<ClientResponse> findAllClients() {
        return clientRepository.findAll().stream()
                .map(ClientResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //Função responsavel por mostrar os clientes com paginação
    
    public Page<ClientResponse> getFilteredClients(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        Page<Client> foundClients = clientRepository.findAll(pageRequest);
        return foundClients.map(ClientResponse::fromEntity);
    }

    @Transactional(readOnly = true)
    public ClientResponseProfileDetails getClientProfileById(int id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NoCustomersFoundByIdException());
        return ClientResponseProfileDetails.fromEntity(client);
    }

    public ClientResponseProfileDetails getAuthenticatedClientProfile() {
        // Pega o objeto do usuário logado no contexto de segurança.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof Client client)) {
            throw new IllegalStateException("O usuário autenticado não é um cliente válido.");
        }

        // Usa o metodo de fábrica corrigido para criar o DTO com o ID.
        return ClientResponseProfileDetails.fromEntity(client);
    }

    @Transactional
    public void updateClientByAdmin(int id, ClientProfileUpdateRequest profileRequest) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NoCustomersFoundByIdException("Cliente com ID " + id + " não encontrado."));

        // Atualiza os dados do cliente com as informações do formulário
        client.setName(profileRequest.name());
        client.setTelephone(profileRequest.telephone());

        // Lógica para atualizar o endereço
        if (profileRequest.address() != null) {
            var addressRequest = profileRequest.address();
            Address address = client.getAddress();
            if (address == null) {
                address = new Address();
                client.setAddress(address);
            }
            address.setStreet(addressRequest.street());
            address.setNumber_house(addressRequest.number_house());
            address.setNeighborhood(addressRequest.neighborhood());
            address.setCep(addressRequest.cep());
            address.setCity(addressRequest.city());
            address.setState(addressRequest.state());
            address.setComplement(addressRequest.complement());
            address.setCountry(addressRequest.country());
        }

        clientRepository.save(client);
    }

    @Transactional
    public void updateAuthenticatedClientProfile(ClientProfileUpdateRequest profileRequest) {
        Client client = getAuthenticatedClient();

        client.setName(profileRequest.name());
        client.setTelephone(profileRequest.telephone());

        if (profileRequest.address() != null) {
            var addressRequest = profileRequest.address();

            Address address = client.getAddress();

            if (address == null) {
                // Novo endereço: criar e salvar antes de associar ao cliente
                address = new Address();
                address.setStreet(addressRequest.street());
                address.setNumber_house(addressRequest.number_house());
                address.setNeighborhood(addressRequest.neighborhood());
                address.setCep(addressRequest.cep());
                address.setCity(addressRequest.city());
                address.setState(addressRequest.state());
                address.setComplement(addressRequest.complement());
                address.setCountry(addressRequest.country());

                address = addressRepository.save(address); // salvar para gerar ID
                client.setAddress(address); // associar ao cliente
            } else {
                // Endereço já existe → atualizar
                address.setStreet(addressRequest.street());
                address.setNumber_house(addressRequest.number_house());
                address.setNeighborhood(addressRequest.neighborhood());
                address.setCep(addressRequest.cep());
                address.setCity(addressRequest.city());
                address.setState(addressRequest.state());
                address.setComplement(addressRequest.complement());
                address.setCountry(addressRequest.country());
            }
        }

        clientRepository.save(client);
    }

    // --- MÉTODOS PRIVADOS AUXILIARES ---
    private Client getAuthenticatedClient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof Client)) {
            throw new IllegalStateException("O usuário não está autenticado ou o principal não é uma instância de Cliente.");
        }
        return (Client) authentication.getPrincipal();
    }

    private void updateClientDataFromAdmin(Client client, ClientRequest request) {
        client.setName(request.name());
        client.setTelephone(request.telephone());
        client.setEmail(request.email());
        // A lógica de atualização de endereço foi REMOVIDA daqui, pois o DTO não a suporta.
    }

    private void updateClientDataFromProfile(Client client, ClientProfileUpdateRequest request) {
        client.setName(request.name());
        client.setTelephone(request.telephone());

        if (request.address() != null) {
            Address address = client.getAddress();
            if (address == null) {
                address = new Address();
                client.setAddress(address);
            }
            var addressRequest = request.address();
            address.setStreet(addressRequest.street());
            address.setNumber_house(addressRequest.number_house());
            address.setNeighborhood(addressRequest.neighborhood());
            address.setCep(addressRequest.cep());
            address.setCity(addressRequest.city());
            address.setState(addressRequest.state());
            address.setComplement(addressRequest.complement());
            address.setCountry(addressRequest.country());
        }
    }

    @Transactional
    public void deleteClientById(int id) {
        // 1. Busca o cliente ou lança uma exceção se não for encontrado.
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NoCustomersFoundByIdException("Cliente com ID " + id + " não encontrado."));

        // 2. Regra de Negócio: Verifica se o cliente possui empréstimos ativos.
        List<Loan> activeLoans = loanRepository.findActiveLoansByClientId(id);
        if (!activeLoans.isEmpty()) {
            throw new IllegalStateException("Não é possível excluir um cliente com empréstimos ativos. Por favor, verifique as devoluções.");
        }

        // 3. Se todas as verificações passarem, o cliente é excluído.
        // Graças ao CascadeType.ALL nas entidades, o endereço e os empréstimos (se houver) serão removidos junto.
        clientRepository.delete(client);
    }
}