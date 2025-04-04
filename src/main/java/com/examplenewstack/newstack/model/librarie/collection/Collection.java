package com.examplenewstack.newstack.model.librarie.collection;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "collection")

public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private int total_quantity;

    @Column(nullable = false)
    private int total_available;

    @Column(nullable = false)
    private String location;

    //Builders
    public Collection() {}

    public Collection(int total_quantity, int total_available, String location) {
        this.total_quantity = total_quantity;
        this.total_available = total_available;
        this.location = location;
    }

    //Getters and Setters
    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getTotal_available() {
        return total_available;
    }

    public void setTotal_available(int total_available) {
        this.total_available = total_available;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
