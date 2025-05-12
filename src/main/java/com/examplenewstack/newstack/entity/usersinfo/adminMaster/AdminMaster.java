package com.examplenewstack.newstack.model.usersinfo.adminMaster;

import com.examplenewstack.newstack.model.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "admin_master")
public class AdminMaster extends User {
    public AdminMaster() { super(); }

    public AdminMaster(String name, String CPF, String email, String telephone, String password) {
        super(name, CPF, email, telephone, password);
    }

    @Override
    public User toUser() {
        return super.toUser();
    }
}
