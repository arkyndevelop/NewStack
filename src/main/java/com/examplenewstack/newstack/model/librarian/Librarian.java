package com.examplenewstack.newstack.model.librarian;

import com.examplenewstack.newstack.model.User;
import jakarta.persistence.*;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "librarian")
public class Librarian extends User {
    public Librarian() { super(); }

    public Librarian(String name, String CPF, String email, String telephone, String password) {
        super(name, CPF, email, telephone, password);
    }

    @Override
    public User toUser() {
        return super.toUser();
    }
}
