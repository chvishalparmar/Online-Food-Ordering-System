package com.food.ordersystem.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.User;
import com.food.ordersystem.security.JwtAuthRequest;
import com.food.ordersystem.security.JwtTokenUtil;
import com.food.ordersystem.services.CreateUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicAuthController {

   
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    private final CreateUserService createUserService;
    

    @GetMapping("/test")
    public String getTest() {
        return new String("Application is working");
    }

    @PostMapping("/newuser")
    public ResponseEntity<User> CreateNewUser(@Valid @RequestBody UserDto userDto) {

        User  createdUser = createUserService.saveUser(userDto);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        
    }
    

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody JwtAuthRequest request) {
         try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtTokenUtil.generateToken(userDetails);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            //log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
    

    
    
    
}
