package com.food.ordersystem.services;

import java.util.List;

import com.food.ordersystem.dto.DishDto;
import com.food.ordersystem.enitites.Dish;

public interface DishService {

    Dish saveDish(DishDto dishDto);
    Dish upadateDish(DishDto dishDto);
    Dish getDish(DishDto dishDto);
    boolean deleteDish(DishDto dishDto);
    List<Dish> checkItemAvaibility();
    Dish upadteavailability(DishDto dishDto);
    
}
