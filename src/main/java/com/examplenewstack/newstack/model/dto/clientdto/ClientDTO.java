package com.examplenewstack.newstack.model.dto.clientdto;

import com.examplenewstack.newstack.model.User;
import com.examplenewstack.newstack.model.dto.UserDTO;
import com.examplenewstack.newstack.model.usersinfo.client.Client;

public class ClientDTO extends UserDTO {
    public ClientDTO() { super();}

    public ClientDTO(String name, String cpf, String email, String telephone, String password, String confirmPassword) {
        super(name, cpf, email, telephone, password, confirmPassword);
    }

    @Override
    public Client toUser() {
        Client client = new Client();
        // Setar os campos da superclasse
        client.setName(this.getName());
        client.setCPF(this.getCpf());
        client.setEmail(this.getEmail());
        client.setTelephone(this.getTelephone());
        client.setPassword(this.getPassword());

        return client;
    }
}
