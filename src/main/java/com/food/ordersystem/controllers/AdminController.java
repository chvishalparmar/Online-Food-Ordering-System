package com.food.ordersystem.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/start")
    public String getStart() {
        return new String("inside admin controller");
    }
    
    
}
