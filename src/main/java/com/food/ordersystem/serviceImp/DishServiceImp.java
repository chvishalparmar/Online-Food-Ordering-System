package com.food.ordersystem.serviceImp;

import org.springframework.stereotype.Service;

import com.food.ordersystem.dto.DishDto;
import com.food.ordersystem.enitites.Dish;

import com.food.ordersystem.exceptions.DishNotFoundException;
import com.food.ordersystem.repo.DishRepo;
import com.food.ordersystem.services.DishService;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import com.food.ordersystem.utils.ValueMapper;

@Service
@RequiredArgsConstructor
public class DishServiceImp implements DishService {

    private final DishRepo dishRepo;

    private final ValueMapper valueMapper;

    @Override
    public Dish saveDish(DishDto dishDto) {
        Optional<Dish> finddish = dishRepo.findByName(dishDto.getName());
        if(finddish.isEmpty()){
            Dish dish = valueMapper.dishDto_To_Dish(dishDto);
            return dishRepo.save(dish);
        }else{
            return null;
        }
    }

    @Override
    public Dish upadateDish(DishDto dishDto) {
      Dish dish = getDish(dishDto);
      dish = valueMapper.updateDish(dishDto, dish);
      return dishRepo.save(dish);
      
    }

    @Override
    public Dish getDish(DishDto dishDto) {
       Dish dish = dishRepo.findByName(dishDto.getName())
       .orElseThrow(() -> new DishNotFoundException("Dish not Found with Name : " + dishDto.getName()));
        return dish;
    }

    @Override
    public boolean deleteDish(DishDto dishDto) {
       Dish dish = getDish(dishDto);
       try{
        dishRepo.delete(dish);
        return true;
       }catch (Exception e){
        return false;
       }
    }

    @Override
    public List<Dish> checkItemAvaibility() {
        return dishRepo.findAvailableDishes();
    }

    @Override
    public Dish upadteavailability(DishDto dishDto) {
        Dish dish = getDish(dishDto);
        dish.setAvailability(dishDto.getAvailability());
        return dishRepo.save(dish);

    }
    
}
