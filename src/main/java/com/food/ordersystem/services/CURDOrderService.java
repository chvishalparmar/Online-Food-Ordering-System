package com.food.ordersystem.services;

import com.food.ordersystem.dto.OrderDto;
import com.food.ordersystem.enitites.Orders;

import java.util.HashMap;
import java.util.List;
public interface CURDOrderService {
    
    Orders createOrder(OrderDto orderDto);
    Orders updateOrder(OrderDto orderDto);
    List<Orders> getOrderStatus(String status , String username);
    List<Orders> getOrderStatusForAdmin(String status);
    HashMap<String , Integer> getOrdersQunatity(String status);
    Orders getOrder(OrderDto orderDto);
    Orders cancelOrder(OrderDto orderDto);
    Orders updateStatus(Long id , String status);
   

}
