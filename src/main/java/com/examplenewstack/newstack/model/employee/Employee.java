package com.examplenewstack.newstack.model.employee;

import com.examplenewstack.newstack.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "librarian")
public class Employee extends User {
    public Employee() { super(); }

    public Employee(String name, String CPF, String email, String telephone, String password) {
        super(name, CPF, email, telephone, password);
    }

    @Override
    public User toUser() {
        return super.toUser();
    }
}
