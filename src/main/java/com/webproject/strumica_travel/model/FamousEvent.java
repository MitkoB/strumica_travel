package com.webproject.strumica_travel.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class FamousEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 5000)
    private String description;
    private LocalDateTime start;
    @Column(name = "finish")
    private LocalDateTime end;
    private String picture;
    private String location;
    public FamousEvent() {
    }
    public FamousEvent(Long id, String title, String description, LocalDateTime start, LocalDateTime end) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
    }
    public FamousEvent(Long id,String title, String description, LocalDateTime start, LocalDateTime end,String picture, String location) {
        super();
        this.id=id;
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
        this.picture=picture;
        this.location=location;
    }
    public FamousEvent(String title, String description, LocalDateTime start, LocalDateTime end,String picture, String location) {
        super();
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
        this.picture=picture;
        this.location=location;
    }

}
