package com.examplenewstack.newstack.entity.usersinfo.address;

import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import jakarta.persistence.*;


@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
    @OneToOne(mappedBy = "address")
    private Client client;

    public Address() {}

    public Address(String number_house, String street, String cep, String complement, String neighborhood, String city, String state, String country) {
        this.number_house = number_house;
        this.street = street;
        this.cep = cep;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getNumber_house() {
        return number_house;
    }

    public void setNumber_house(String number_house) {
        this.number_house = number_house;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}