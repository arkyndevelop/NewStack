package com.examplenewstack.newstack.domain.address;

import com.examplenewstack.newstack.domain.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "address")

//Getters and Setters, Constructor and NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String number_house;

    @Column(nullable = false,length = 180)
    private String street;

    @Column(nullable = false,length = 10)
    private String cep;

    private String complement;

    @Column(nullable = false,length = 110)
    private String neighborhood;

    @Column(nullable = false,length = 220)
    private String city;

    @Column(nullable = false,length = 75)
    private String state;

    @Column(nullable = false,length = 64)
    private String country;

    // Relacionamento 1:1 com Client
    // Este lado é o inverso da relação (não possui @JoinColumn)
    @OneToOne(mappedBy = "address",fetch = FetchType.LAZY)
    //@JoinTable(foreignKey = )
    private Client client;


}