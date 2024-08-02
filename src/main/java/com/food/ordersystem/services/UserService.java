package com.food.ordersystem.services;

import org.springframework.stereotype.Service;

import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.User;

@Service
public interface UserService {

    User saveUser(UserDto userDto);
    User getUser(UserDto userDto);
    User updateUser(UserDto userDto);
    boolean deleteUser(UserDto userDto);
    
} 