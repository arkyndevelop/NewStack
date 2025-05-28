package com.examplenewstack.newstack.admin;

import com.examplenewstack.newstack.core.entity.User;
import jakarta.persistence.*;

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
