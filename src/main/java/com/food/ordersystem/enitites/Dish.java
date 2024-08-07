package com.food.ordersystem.enitites;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.food.ordersystem.enums.Cuisine;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    private String description;
    
    @Column(nullable = false)
    private Double price;
    
    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;
    
    private Boolean availability;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Review> reviews;
    
    private Double avgRating;

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        updateAvgRating();
    }
    
    public void updateAvgRating() {
        if (reviews == null || reviews.isEmpty()) {
            avgRating = 0.0;
        } else {
            double sum = reviews.stream()
                    .mapToDouble(review -> review.getRating() == null ? 0 : review.getRating())
                    .sum();
            avgRating = sum / reviews.size();
        }
    }
    
  
}
