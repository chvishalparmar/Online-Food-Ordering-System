package com.food.ordersystem.utils;

import org.springframework.stereotype.Service;

import com.food.ordersystem.enums.Cuisine;
import com.food.ordersystem.enums.DeliveryStatus;
import com.food.ordersystem.exceptions.InvalidEnumValueException;

@Service
public class EnumValueChecker {

     public Cuisine checkCuisine(String cuisine) {
        try {
            return Cuisine.valueOf(cuisine.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(cuisine.concat(" Invaild cuisine! Available cuisine - Chinese, Continental, North_Indian, South_Indian"));
        }
    }

    public DeliveryStatus checkDeliveryStatus(String status){
        try{
            return DeliveryStatus.valueOf(status.toUpperCase());
        }catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(status.concat(" Invaild Status Query! Available Status Query - PENDING, SHIPPED, DELIVERED, CANCELED"));
        }
    }
    
}
