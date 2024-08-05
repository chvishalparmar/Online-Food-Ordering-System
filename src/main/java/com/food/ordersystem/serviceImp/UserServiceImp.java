package com.food.ordersystem.serviceImp;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.User;
import com.food.ordersystem.exceptions.UserNotFoundException;
import com.food.ordersystem.repo.UserRepo;
import com.food.ordersystem.services.UserService;
import com.food.ordersystem.utils.ValueMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepo userRepo;

    private final ValueMapper valueMapper;

    @Override
    public User saveUser(UserDto userDto) {
        Optional<User> finduser = userRepo.findByUserName(userDto.getUsername());
        if (finduser.isEmpty()) {
            User user = valueMapper.userDto_To_User(userDto);
            user.setRoles("USER");
            return userRepo.save(user);
        } else {
            return null;
        }

    }

    @Override
    public User getUser(UserDto userDto) {
        User user = userRepo.findByUserName(userDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found with UserName: " + userDto.getUsername()));

        return user;
    }

    @Override
    public User updateUser(UserDto userDto) {
        User user = getUser(userDto);
        user = valueMapper.userUpdate(userDto, user);
        return userRepo.save(user);
    }

    @Override
    public boolean deleteUser(UserDto userDto) {

        User user = getUser(userDto);
        try {
            userRepo.delete(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
