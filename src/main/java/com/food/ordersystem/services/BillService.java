package com.food.ordersystem.services;

import com.food.ordersystem.dto.BillDto;
import com.food.ordersystem.dto.OrderDto;

public interface BillService {
    BillDto getBill(OrderDto orderDto);
}
