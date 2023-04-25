package com.example.rapportservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Rapport {
    @Id
    @GeneratedValue
    private Long id;
    //private Long id_delegue;
    //private Long id_visite;

    //hado incase we save it as a file
    //private String path;
    //private String description;
    private String title;
    private String content;

    public Rapport(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
