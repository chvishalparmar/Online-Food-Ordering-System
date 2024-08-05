package com.food.ordersystem.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.ordersystem.dto.APIResponse;
import com.food.ordersystem.enitites.Dish;
import com.food.ordersystem.enums.ApiResponseStatus;
import com.food.ordersystem.services.DishService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/public/menu")
@RequiredArgsConstructor
public class MenuController {
    private final DishService dishService;

    @GetMapping("/get")
    public ResponseEntity<APIResponse<?>> getMethodName() {
        List<Dish> aviableDish = dishService.checkItemAvaibility();
        APIResponse<List<Dish>> response = APIResponse.<List<Dish>>builder()
                                    .status(ApiResponseStatus.SUCCESS.toString())
                                    .results(aviableDish)
                                    .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
