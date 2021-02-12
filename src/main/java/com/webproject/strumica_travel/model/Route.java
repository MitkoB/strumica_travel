package com.webproject.strumica_travel.model;

import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 5000)
    private String description;
    private String duration;
    @ManyToMany
    private List<TouristAttraction> touristAttractions;
    private Integer likes;
    public Route() {
    }

    public Route(String name, String description, String duration, List<TouristAttraction> touristAttractions) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.touristAttractions = touristAttractions;
        this.likes=0;
    }
}
