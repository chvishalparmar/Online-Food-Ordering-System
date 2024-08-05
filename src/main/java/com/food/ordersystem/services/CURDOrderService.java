package com.food.ordersystem.services;

import com.food.ordersystem.dto.OrderDto;
import com.food.ordersystem.enitites.Orders;
import java.util.List;
public interface CURDOrderService {
    
    Orders createOrder(OrderDto orderDto);
    Orders updateOrder(OrderDto orderDto);
    List<Orders> getOrder(String status , String username);
    Orders cancelOrder(OrderDto orderDto);
}
