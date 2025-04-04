package com.examplenewstack.newstack.model.dto.clientdto;

import com.examplenewstack.newstack.model.User;
import com.examplenewstack.newstack.model.dto.UserDTO;

public class ClienteDTO extends UserDTO {
    public ClienteDTO() { super();}

    public ClienteDTO(String name, String cpf, String email, String telephone, String password, String confirmPassword) {
        super(name, cpf, email, telephone, password, confirmPassword);
    }

    @Override
    public User toUser() {
        return super.toUser();
    }
}
