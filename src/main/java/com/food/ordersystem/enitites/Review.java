package com.food.ordersystem.enitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String comment;
    
    @Column(nullable = false)
    private Integer rating;
    
    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
}
