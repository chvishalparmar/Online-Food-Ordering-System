package com.food.ordersystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.food.ordersystem.enitites.User;

public interface UserRepo extends JpaRepository<User , Long>  {
    Optional<User> findByUserName(String username);
}
