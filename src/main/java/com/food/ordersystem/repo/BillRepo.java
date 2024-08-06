package com.food.ordersystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.ordersystem.enitites.Bill;

public interface BillRepo extends JpaRepository<Bill , Long>{
    
}
