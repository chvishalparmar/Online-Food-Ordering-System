package com.food.ordersystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.ordersystem.enitites.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem , Long> {
    
}
