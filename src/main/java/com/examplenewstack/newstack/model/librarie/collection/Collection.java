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
    private int totalQuantity;

    @Column(nullable = false)
    private int totalAvailable;

    @Column(nullable = false)
    private String location;

    public Collection() {}

    public Collection(int totalQuantity, int totalAvailable, String location) {
        this.totalQuantity = totalQuantity;
        this.totalAvailable = totalAvailable;
        this.location = location;
    }

    public UUID getId() { return id; }

    public int getTotalQuantity() { return totalQuantity; }

    public void setTotalQuantity(int totalQuantity) { this.totalQuantity = totalQuantity; }

    public int getTotalAvailable() { return totalAvailable; }

    public void setTotalAvailable(int totalAvailable) { this.totalAvailable = totalAvailable; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }
}
