package com.examplenewstack.newstack.entity.dto.clientdto;

import com.examplenewstack.newstack.entity.dto.UserDTO;
import com.examplenewstack.newstack.entity.usersinfo.client.Client;

public class ClientDTO extends UserDTO {
    public ClientDTO() { super();}

    public ClientDTO(String name, String cpf, String email, String telephone, String password, String confirmPassword) {
        super(name, cpf, email, telephone, password, confirmPassword);
    }

    // Metodo responsável por adicionar valores aos atributos de uma classe
    @Override
    public Client toUser() {
        Client client = new Client();
        // Setar os campos da superclasse
        client.setName(this.getName());
        client.setCPF(this.getCPF());
        client.setEmail(this.getEmail());
        client.setTelephone(this.getTelephone());
        client.setPassword(this.getPassword());

        return client;
    }
}
