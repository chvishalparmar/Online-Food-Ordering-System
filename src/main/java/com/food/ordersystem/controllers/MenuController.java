package com.food.ordersystem.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.ordersystem.dto.APIResponse;
import com.food.ordersystem.dto.DishDto;
import com.food.ordersystem.enitites.Dish;
import com.food.ordersystem.enums.ApiResponseStatus;
import com.food.ordersystem.enums.Cuisine;
import com.food.ordersystem.services.DishService;
import com.food.ordersystem.utils.EnumValueChecker;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/public/menu")
@RequiredArgsConstructor
public class MenuController {
    private final DishService dishService;
    private final EnumValueChecker enumValueChecker;

    @GetMapping("/get")
    public ResponseEntity<APIResponse<?>> getMenu() {
        List<Dish> aviableDish = dishService.checkItemAvaibility();
        APIResponse<List<Dish>> response = APIResponse.<List<Dish>>builder()
                                    .status(ApiResponseStatus.SUCCESS.toString())
                                    .results(aviableDish)
                                    .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/details/{name}")
    public ResponseEntity<APIResponse<?>> getDetails(@PathVariable String name) {
        DishDto dishDto = new DishDto();
        dishDto.setName(name);

        Dish dish = dishService.getDish(dishDto);

        APIResponse<Dish> responseDTO = APIResponse.<Dish>builder()
                                        .status(ApiResponseStatus.SUCCESS.toString())
                                        .results(dish)
                                        .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/byrating")
    public ResponseEntity<APIResponse<?>> getByRating() {
        List<Dish> aviableDish = dishService.checkItemAvaibility();
        aviableDish.sort(Comparator.comparing(Dish :: getAvgRating).reversed());
        APIResponse<List<Dish>> responseDTO = APIResponse.<List<Dish>>builder()
                                            .status(ApiResponseStatus.SUCCESS.toString())
                                            .results(aviableDish)
                                            .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    

    @GetMapping("/bycuisine/{name}")
    public ResponseEntity<APIResponse<?>> byCuisine(@PathVariable String name) {
        Cuisine type = enumValueChecker.checkCuisine(name);
        List<Dish> aviableDish = dishService.checkItemAvaibility();
        
        List<Dish> filteredDishes = aviableDish .stream()
                                    .filter(dish -> dish.getCuisine() == type)
                                    .collect(Collectors.toList());

        APIResponse<List<Dish>> responseDTO = APIResponse.<List<Dish>>builder()
                                    .status(ApiResponseStatus.SUCCESS.toString())
                                    .results(filteredDishes)
                                    .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        
    }
    
    
    
}
