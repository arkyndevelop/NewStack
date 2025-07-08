// Conteúdo de src/main/java/aws/Picture.java
package com.examplenewstack.newstack.config.aws;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column; // Importe o Column

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeOriginal;

    @Column(nullable = false, unique = true)
    private String nomeNoS3;

    @Column(nullable = false, length = 512)
    private String urlS3;

    // Construtor com parâmetros
    public Picture(String nomeOriginal, String nomeNoS3, String urlS3) {
        this.nomeOriginal = nomeOriginal;
        this.nomeNoS3 = nomeNoS3;
        this.urlS3 = urlS3;
    }
}