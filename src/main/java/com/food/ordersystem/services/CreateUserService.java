package com.food.ordersystem.services;

import org.springframework.stereotype.Service;

import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.User;

@Service
public interface CreateUserService {

    User saveUser(UserDto userDto);
    
} 