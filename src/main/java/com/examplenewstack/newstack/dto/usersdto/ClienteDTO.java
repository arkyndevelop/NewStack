package com.examplenewstack.newstack.dto.usersdto;

import com.examplenewstack.newstack.model.usersinfo.User;

public class ClienteDTO extends UserDTO{
    public ClienteDTO() { super();}

    public ClienteDTO(String name, String cpf, String email, String telephone, String password, String confirmPassword) {
        super(name, cpf, email, telephone, password, confirmPassword);
    }

    @Override
    public User toUser() {
        return super.toUser();
    }
}
