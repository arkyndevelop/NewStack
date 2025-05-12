package com.examplenewstack.newstack.entity.librarie.collection;


import com.examplenewstack.newstack.entity.librarie.lore.Lore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "collection")

public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int total_quantity;

    @Column(nullable = false)
    private int total_available;

    @Column(nullable = false)
    private String location;

    // Relacionamento com Lore
    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    private List<Lore> lores;

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
