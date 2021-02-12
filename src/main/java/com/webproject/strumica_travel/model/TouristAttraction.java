package com.webproject.strumica_travel.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TouristAttraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    @Column(length = 5000)
    private String description;
    @Column(length = 5000)
    private String pictures;
    @OneToMany(mappedBy = "touristAttraction")
    private List<Review> reviews;

    public TouristAttraction() {
    }

    public TouristAttraction(String name, String location, String description, String pictures) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.pictures = pictures;
        this.reviews=new ArrayList<>();
    }
}
