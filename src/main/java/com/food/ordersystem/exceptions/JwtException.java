package com.food.ordersystem.exceptions;

public class JwtException extends RuntimeException {
    public JwtException(String msg){
        super(msg);
    }
}
