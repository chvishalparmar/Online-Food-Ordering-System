package com.food.ordersystem.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.food.ordersystem.enitites.Dish;



public interface DishRepo extends JpaRepository<Dish , Long> {
    Optional<Dish> findByName(String dishname);
    @Query("SELECT d FROM Dish d WHERE d.availability = true")
    List<Dish> findAvailableDishes();
}
