package com.food.ordersystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, max = 8, message = "Username must be between 3 and 50 characters")
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 4, max = 8 , message = "Password must be at least 8 characters")
    private String password;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Address is mandatory")
    private String address;
    
}
