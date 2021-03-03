package com.webproject.strumica_travel.model;


import com.webproject.strumica_travel.model.enumeration.AttractionType;
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
    private String mainPicture;
    private String pictures;
    @OneToMany(mappedBy = "touristAttraction")
    private List<Review> reviews;
    @Enumerated(value = EnumType.STRING)
    private AttractionType type;
    public TouristAttraction() {
    }

    public TouristAttraction(String name, String location, String description,String mainPicture, String pictures,AttractionType type) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.pictures = pictures;
        this.mainPicture=mainPicture;
        this.reviews=new ArrayList<>();
        this.type=type;
    }

    public String getPictures() {
        return this.pictures;
    }

    public void setName(String name) {
        this.name=name;
    }
}
