package com.examplenewstack.newstack.model.usersinfo.address;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String cep;

    private String complement;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    private String country;

    //Número da casa
    @Column(nullable = false)
    private String number_house;

}