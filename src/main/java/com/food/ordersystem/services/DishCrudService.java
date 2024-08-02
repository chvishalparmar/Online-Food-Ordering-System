package com.food.ordersystem.services;

import com.food.ordersystem.dto.DishDto;
import com.food.ordersystem.enitites.Dish;

public interface DishCrudService {

    Dish saveDish(DishDto dishDto);
    Dish upadateDish(DishDto dishDto);
    Dish getDish(DishDto dishDto);
    boolean deleteDish(DishDto dishDto);
    
}
