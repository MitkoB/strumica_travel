package com.webproject.strumica_travel.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private TouristAttraction touristAttraction;
    @ManyToOne
    private Route route;
    private LocalDate date;
    @Column(length = 4000)
    private String comment;
    private Integer grade;

    public Review() {
    }

    public Review(User user, TouristAttraction touristAttraction, String comment, Integer grade) {
        this.user = user;
        this.touristAttraction = touristAttraction;
        this.date=LocalDate.now();
        this.comment = comment;
        this.grade = grade;
    }
    public Review(User user, Route route, String comment, Integer grade) {
        this.user = user;
        this.route = route;
        this.date=LocalDate.now();
        this.comment = comment;
        this.grade = grade;
    }
}
