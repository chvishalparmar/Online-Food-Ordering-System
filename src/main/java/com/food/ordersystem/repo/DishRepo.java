package com.food.ordersystem.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.food.ordersystem.enitites.Dish;



public interface DishRepo extends JpaRepository<Dish , Long> {
    Optional<Dish> findByName(String dishname);
}
