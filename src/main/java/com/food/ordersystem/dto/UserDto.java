package com.food.ordersystem.dto;

import com.food.ordersystem.vaildationgroup.CreateGroup;
import com.food.ordersystem.vaildationgroup.UpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {

    @NotBlank(message = "Username is mandatory" , groups = CreateGroup.class)
    @Size(min = 4, max = 8, message = "Username must be between 3 and 50 characters" ,  groups = CreateGroup.class)
    private String username;
    @NotBlank(message = "Password is mandatory" ,  groups = {CreateGroup.class, UpdateGroup.class})
    @Size(min = 4, max = 8 , message = "Password must be at least 8 characters" ,  groups = {CreateGroup.class, UpdateGroup.class})
    private String password;
    @NotBlank(message = "Name is mandatory" ,  groups = {CreateGroup.class, UpdateGroup.class})
    private String name;
    @Email(message = "Email should be valid" ,  groups = {CreateGroup.class, UpdateGroup.class})
    @NotBlank(message = "Email is mandatory" ,  groups = {CreateGroup.class, UpdateGroup.class})
    private String email;
    @NotBlank(message = "Address is mandatory" ,  groups = {CreateGroup.class, UpdateGroup.class})
    private String address;
    
}
