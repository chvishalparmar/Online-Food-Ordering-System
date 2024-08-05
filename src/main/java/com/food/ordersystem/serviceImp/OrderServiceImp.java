package com.food.ordersystem.serviceImp;


import org.springframework.stereotype.Service;

import com.food.ordersystem.dto.OrderDto;
import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.Orders;
import com.food.ordersystem.enitites.User;
import com.food.ordersystem.enums.DeliveryStatus;
import com.food.ordersystem.exceptions.DishNotFoundException;
import com.food.ordersystem.repo.OrderRepo;
import com.food.ordersystem.services.OrderService;
import com.food.ordersystem.services.UserService;
import com.food.ordersystem.utils.ValueMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepo orderRepo;

    private final ValueMapper valueMapper;

    private final UserService userService;

    @Override
    public Orders saveOrder(OrderDto orderDto) {
       Orders orders = valueMapper.orderDto_To_Order(orderDto);
       User user = userService.getUser(orderDto.getUser());
       orders.setUser(user);

       // Now save the Orders entity along with its associated OrderItems
       Orders orderInDB = orderRepo.save(orders);
       return orderInDB;
    }

    @Override
    public Orders upadateOrder(Orders orderDto) {
       return orderRepo.save(orderDto);
    }

    @Override
    public Orders getOrder(OrderDto orderDto) {
       Orders order = orderRepo.findById(orderDto.getOrderNumber())
       .orElseThrow(() -> new DishNotFoundException("Order not Found with id : " + orderDto.getOrderNumber()));
       return order;
    }

    @Override
    public boolean deleteOrder(OrderDto orderDto) {
       Orders order = getOrder(orderDto);
       try{
        orderRepo.delete(order);
        return true;
       }catch (Exception e){
        return false;
       }
    }

   @Override
   public List<Orders> getOrderByDeliverySatus(UserDto userDto, DeliveryStatus status) {
       User user = userService.getUser(userDto);
       return orderRepo.findByUserAndDeliveryStatus(user, status);
   }
    
}
