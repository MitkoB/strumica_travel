package com.webproject.strumica_travel.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TraditionalFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 5000)
    private String description;
    @Column(length = 5000)
    private String pictures;

    public TraditionalFood() {
    }

    public TraditionalFood(String name, String description, String pictures) {
        this.name = name;
        this.description = description;
        this.pictures = pictures;
    }
}
