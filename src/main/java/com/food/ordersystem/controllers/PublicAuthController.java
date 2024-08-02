package com.food.ordersystem.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.ordersystem.dto.APIResponse;
import com.food.ordersystem.dto.ErrorDto;
import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.User;
import com.food.ordersystem.security.JwtAuthRequest;
import com.food.ordersystem.security.JwtTokenUtil;
import com.food.ordersystem.services.UserService;
import com.food.ordersystem.vaildationgroup.CreateGroup;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;




@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicAuthController {

   
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserService userService;

    public static final String SUCCESS = "Success";

    public static final String FAILED = "Failed";
    

    @GetMapping("/test")
    public ResponseEntity<APIResponse<String>> getTest() {
        APIResponse<String> responseDTO = APIResponse.<String>builder()
                                        .status(SUCCESS)
                                        .results(new String("Application is working"))
                                        .build();
        return  new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/newuser")
    public ResponseEntity<APIResponse<?>> CreateNewUser(@Validated(CreateGroup.class) @RequestBody UserDto userDto) {

        User createdUser = userService.saveUser(userDto);
        
        if(createdUser !=null){
        APIResponse<User> responseDTO = APIResponse.<User>builder()
                                        .status(SUCCESS)
                                        .results(createdUser)
                                        .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }else{
            APIResponse<?> responseDTO = new APIResponse<>();
            responseDTO.setStatus(FAILED);
            responseDTO.setErrors(Collections.singletonList(new ErrorDto("UserName", "UserName Already Present!")));
        return new ResponseEntity<>(responseDTO, HttpStatus.CONFLICT);

        }
        
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
