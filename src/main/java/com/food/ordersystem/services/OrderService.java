package com.food.ordersystem.services;

import com.food.ordersystem.dto.OrderDto;
import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.Orders;
import com.food.ordersystem.enums.DeliveryStatus;
import java.util.List;

public interface OrderService {
    Orders saveOrder(OrderDto orderDto);
    Orders upadateOrder(Orders order);
    Orders getOrder(OrderDto orderDto);
    boolean deleteOrder(OrderDto orderDto);
    List<Orders> getOrderByDeliverySatus(UserDto userDto , DeliveryStatus status);
    List<Orders> getOrdersForAdmin(DeliveryStatus status);
}
