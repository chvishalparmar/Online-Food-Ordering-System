package com.food.ordersystem.repo;

import com.food.ordersystem.enitites.Orders;
import com.food.ordersystem.enitites.User;
import com.food.ordersystem.enums.DeliveryStatus;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders , Long> {
    List<Orders> findByUserAndDeliveryStatus(User user, DeliveryStatus deliveryStatus);
}
