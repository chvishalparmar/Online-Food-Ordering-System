package com.food.ordersystem.utils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.food.ordersystem.dto.DishDto;
import com.food.ordersystem.dto.OrderDto;
import com.food.ordersystem.dto.OrderItemDTO;
import com.food.ordersystem.dto.ReviewDto;
import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.Dish;
import com.food.ordersystem.enitites.OrderItem;
import com.food.ordersystem.enitites.Orders;
import com.food.ordersystem.enitites.Review;
import com.food.ordersystem.enitites.User;
import com.food.ordersystem.enums.Cuisine;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValueMapper {

   
    private final PasswordEncoder passwordEncoder;
    private final EnumValueChecker enumValueChecker;


    public  User userDto_To_User(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setAddress(userDto.getAddress());
        return user;
    }

    public User userUpdate(UserDto userDto , User user){
        user.setAddress(userDto.getAddress());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        return user;

    }

    public Dish dishDto_To_Dish(DishDto dishDto){
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        dish.setAvailability(dishDto.getAvailability());
        dish.setDescription(dishDto.getDescription());
        dish.setAvgRating(0.0);
        dish.setPrice(dishDto.getPrice());
        Cuisine cuisine = enumValueChecker.checkCuisine(dishDto.getCuisine());
        dish.setCuisine(cuisine);
        return dish;
    
    }

    public Dish updateDish(DishDto dishDto , Dish dish){
        dish.setAvailability(dishDto.getAvailability());
        dish.setDescription(dishDto.getDescription());
        dish.setPrice(dishDto.getPrice());
        Cuisine cuisine = enumValueChecker.checkCuisine(dishDto.getCuisine());
        dish.setCuisine(cuisine);
        return dish;
    }

    

    public Orders orderDto_To_Order(OrderDto orderDto){
        Orders order = new Orders();
        order.setDeliveryAddress(orderDto.getDeliveryAddress());
        order.setDeliveryStatus(orderDto.getDeliveryStatus());
        List<OrderItem> orderItem = orderItemDto_to_OrderItem(orderDto.getOrderItems());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setOrderItems(orderItem);
        return order;
    }

    public List<OrderItem> orderItemDto_to_OrderItem(List<OrderItemDTO> orderItemDTO  ){
        List<OrderItem> items = new ArrayList<>();
       
        for(OrderItemDTO i : orderItemDTO ){ 
            OrderItem temp = new OrderItem();
            temp.setItemName(i.getItemName());
            temp.setQuantity(i.getQuantity());
            items.add(temp);
        }
        return items;
    }

    public Review ReviewDto_To_Review(ReviewDto reviewDto){
        Review review = new Review();
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        return review;

    }
    
}
