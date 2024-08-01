package com.food.ordersystem.utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.User;

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
    
}
