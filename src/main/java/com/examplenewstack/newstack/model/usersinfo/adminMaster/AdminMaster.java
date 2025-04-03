package com.examplenewstack.newstack.model.usersinfo.adminMaster;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "admin_master")
public class AdminMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;

    private String email;

}
