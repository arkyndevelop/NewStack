package com.examplenewstack.newstack.entity.librarie.collection;


import com.examplenewstack.newstack.entity.librarie.lore.Lore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "collection")

//Getters and Setters, Constructor and NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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


}
