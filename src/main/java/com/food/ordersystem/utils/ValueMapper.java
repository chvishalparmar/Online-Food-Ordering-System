package com.food.ordersystem.utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.food.ordersystem.dto.DishDto;
import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.Dish;
import com.food.ordersystem.enitites.User;
import com.food.ordersystem.enums.Cuisine;
import com.food.ordersystem.exceptions.InvalidEnumValueException;

@Service
public class ValueMapper {

   
    private final PasswordEncoder passwordEncoder;

    public ValueMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public  User userDtoToUser(UserDto userDto){
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

    public Dish dishDtotoDish(DishDto dishDto){
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        dish.setAvailability(dish.getAvailability());
        dish.setDescription(dishDto.getDescription());
        dish.setAvgRating(0.0);
        dish.setPrice(dishDto.getPrice());
        Cuisine cuisine = map(dishDto.getCuisine());
        dish.setCuisine(cuisine);
        return dish;
    
    }

    public Dish updateDish(DishDto dishDto , Dish dish){
        dish.setAvailability(dishDto.getAvailability());
        dish.setDescription(dishDto.getDescription());
        dish.setPrice(dishDto.getPrice());
        Cuisine cuisine = map(dishDto.getCuisine());
        dish.setCuisine(cuisine);
        return dish;
    }

     public Cuisine map(String cuisine) {
        try {
            return Cuisine.valueOf(cuisine.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(cuisine.concat(" Invaild cuisine! Available cuisine - Chinese, continental, north-Indian, south-Indian"));
        }
    }
    
}
