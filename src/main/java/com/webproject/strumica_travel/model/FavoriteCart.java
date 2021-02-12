package com.webproject.strumica_travel.model;


import com.webproject.strumica_travel.model.enumeration.FavoriteCartStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class FavoriteCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime localDateTime;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private FavoriteCartStatus favoriteCartStatus;
    @ManyToMany
    private List<TouristAttraction> touristAttractionList;
    @ManyToMany
    private List<Route> routeList;

    public FavoriteCart() {
    }

    public FavoriteCart(User user) {
        this.localDateTime=LocalDateTime.now();
        this.user = user;
        this.favoriteCartStatus=FavoriteCartStatus.CREATED;
        this.touristAttractionList=new ArrayList<>();
        this.touristAttractionList=new ArrayList<>();
    }
}
