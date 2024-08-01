package com.food.ordersystem.serviceImp;

import org.springframework.stereotype.Service;

import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.User;
import com.food.ordersystem.repo.UserRepo;
import com.food.ordersystem.services.CreateUserService;
import com.food.ordersystem.utils.ValueMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserServiceImp implements CreateUserService{

    private final UserRepo userRepo;

    private final ValueMapper valueMapper;

    @Override
    public User saveUser(UserDto userDto) {

        User user = valueMapper.userDtoToUser(userDto);
        user.setRoles("USER");
        return userRepo.save(user);

    }
    
}
