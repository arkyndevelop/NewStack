package com.examplenewstack.newstack.model.usersinfo.client;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
}
