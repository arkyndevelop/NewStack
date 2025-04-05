package com.examplenewstack.newstack.model.dto.librariandto;

import com.examplenewstack.newstack.model.User;
import com.examplenewstack.newstack.model.dto.UserDTO;

public class LibrarianDTO extends UserDTO {
    public LibrarianDTO() { super(); }

    public LibrarianDTO(String name, String cpf, String email, String telephone, String password, String confirmPassword) {
        super(name, cpf, email, telephone, password, confirmPassword);
    }

    // Metodo responsável por adicionar valores aos atributos de uma classe
    @Override
    public User toUser() {
        return super.toUser();
    }
}
