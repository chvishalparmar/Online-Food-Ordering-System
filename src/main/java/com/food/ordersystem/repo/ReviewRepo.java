package com.food.ordersystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.ordersystem.enitites.Dish;
import com.food.ordersystem.enitites.Review;
import com.food.ordersystem.enitites.User;

import java.util.List;


public interface ReviewRepo extends JpaRepository<Review , Long>{
    List<Review> findByUser(User user);
    List<Review> findByDish(Dish dish);
}
