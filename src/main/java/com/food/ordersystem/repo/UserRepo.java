package com.food.ordersystem.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.food.ordersystem.enitites.User;

public interface UserRepo extends JpaRepository<User , Long>  {
    User findByUserName(String username);
}
