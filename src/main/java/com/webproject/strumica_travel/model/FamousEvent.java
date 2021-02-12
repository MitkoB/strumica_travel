package com.webproject.strumica_travel.model;

import com.webproject.strumica_travel.model.enumeration.Season;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class FamousEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 5000)
    private String description;
    @Column(length = 5000)
    private String picture;
    private String location;
    @Enumerated(EnumType.STRING)
    private Season season;

    public FamousEvent() {
    }

    public FamousEvent(String name, String description, String picture, String location, Season season) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.location = location;
        this.season = season;
    }

}
