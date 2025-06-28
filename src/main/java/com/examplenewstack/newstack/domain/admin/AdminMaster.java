package com.examplenewstack.newstack.domain.admin;

import com.examplenewstack.newstack.core.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "admin_master")
public class AdminMaster extends User {

    public AdminMaster(Integer id, String name, String CPF, String email, String telephone, String password, Instant dateRegister, String role) {
        super(id, name, CPF, email, telephone, password, dateRegister, role);
    }

    public AdminMaster() {
    }
}
