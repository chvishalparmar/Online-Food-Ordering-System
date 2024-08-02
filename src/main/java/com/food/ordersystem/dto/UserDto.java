package com.food.ordersystem.dto;

import com.food.ordersystem.vaildationgroup.CreateUserGroup;
import com.food.ordersystem.vaildationgroup.UpdateUserGroup;

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

    @NotBlank(message = "Username is mandatory" , groups = CreateUserGroup.class)
    @Size(min = 4, max = 8, message = "Username must be between 3 and 50 characters" ,  groups = CreateUserGroup.class)
    private String username;
    @NotBlank(message = "Password is mandatory" ,  groups = {CreateUserGroup.class, UpdateUserGroup.class})
    @Size(min = 4, max = 8 , message = "Password must be at least 8 characters" ,  groups = {CreateUserGroup.class, UpdateUserGroup.class})
    private String password;
    @NotBlank(message = "Name is mandatory" ,  groups = {CreateUserGroup.class, UpdateUserGroup.class})
    private String name;
    @Email(message = "Email should be valid" ,  groups = {CreateUserGroup.class, UpdateUserGroup.class})
    @NotBlank(message = "Email is mandatory" ,  groups = {CreateUserGroup.class, UpdateUserGroup.class})
    private String email;
    @NotBlank(message = "Address is mandatory" ,  groups = {CreateUserGroup.class, UpdateUserGroup.class})
    private String address;
    
}
