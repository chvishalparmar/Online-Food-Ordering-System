package com.food.ordersystem.serviceImp;

import com.food.ordersystem.dto.DishDto;
import com.food.ordersystem.dto.OrderDto;
import com.food.ordersystem.dto.OrderItemDTO;
import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.Dish;
import com.food.ordersystem.enitites.Orders;
import com.food.ordersystem.enums.DeliveryStatus;
import com.food.ordersystem.exceptions.DishNotFoundException;
import com.food.ordersystem.services.CURDOrderService;
import com.food.ordersystem.services.DishService;
import com.food.ordersystem.services.OrderService;
import com.food.ordersystem.utils.EnumValueChecker;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurdOrderServiceImp implements CURDOrderService {

    private final DishService dishService;

    private final OrderService orderService;


    private final EnumValueChecker enumValueChecker;

    @Override
    public Orders createOrder(OrderDto orderDto) {

       // List<Dish> orderDishes = new ArrayList<Dish>();
        Map<Dish , Integer> orderDishes = new  HashMap<>();;
        for(OrderItemDTO item : orderDto.getOrderItems()){
          orderDishes.put(checkItemAvaibility(item),  item.getQuantity()); 
        }  
        orderDto.setTotalPrice(getTotalValue(orderDishes));
        orderDto.setDeliveryStatus(DeliveryStatus.PENDING);
        return orderService.saveOrder(orderDto);
       
    }

    private double getTotalValue(Map<Dish , Integer> dishes){

        double total = 0;
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
           // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
           total = total + entry.getKey().getPrice()* entry.getValue();
        }
        return total;
    }

    private Dish checkItemAvaibility(OrderItemDTO orderItemDTO){
        DishDto dishDto = new DishDto();
        dishDto.setName(orderItemDTO.getItemName());
        dishService.getDish(dishDto);

        List<Dish> avaibleDish = dishService.checkItemAvaibility();
        
        if(avaibleDish.stream().anyMatch(dish -> dish.getName().equals(dishDto.getName()))){
            Dish dish=  avaibleDish.stream().filter(dishes -> dishes.getName().equals(dishDto.getName())).findFirst().orElse(null);
            return dish;
        }else{
            throw new DishNotFoundException("Dish You are Ordering is Not Avaible Right Now! Please Check Later !!");
        }

    }

    @Override
    public Orders updateOrder(OrderDto orderDto) {
       Orders order = orderService.getOrder(orderDto);
       order.setDeliveryAddress(orderDto.getDeliveryAddress());
       return orderService.upadateOrder(order);
    }

    @Override
    public List<Orders> getOrder(String status , String username) {
       DeliveryStatus deliveryStatus = enumValueChecker.checkDeliveryStatus(status);
       UserDto dto = new UserDto();
       dto.setUsername(username);
       return orderService.getOrderByDeliverySatus(dto, deliveryStatus);
    }

    @Override
    public Orders cancelOrder(OrderDto orderDto) {
        Orders orders = orderService.getOrder(orderDto);
        orders.setDeliveryStatus(DeliveryStatus.CANCELED);
        return orderService.upadateOrder(orders);
    }

    
    
    
}
