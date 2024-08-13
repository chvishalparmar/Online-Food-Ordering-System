package com.food.ordersystem.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.ordersystem.dto.APIResponse;
import com.food.ordersystem.dto.ErrorDto;
import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.User;
import com.food.ordersystem.enums.ApiResponseStatus;
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


    @GetMapping("/test")
    public ResponseEntity<APIResponse<String>> getTest() {
        APIResponse<String> responseDTO = APIResponse.<String>builder()
                                        .status(ApiResponseStatus.SUCCESS.toString())
                                        .results(new String("Application is working"))
                                        .build();
        return  new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/newuser")
    public ResponseEntity<APIResponse<?>> CreateNewUser(@Validated(CreateGroup.class) @RequestBody UserDto userDto) {

        User createdUser = userService.saveUser(userDto);
        
        if(createdUser !=null){
        APIResponse<User> responseDTO = APIResponse.<User>builder()
                                        .status(ApiResponseStatus.SUCCESS.toString())
                                        .results(createdUser)
                                        .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }else{
            APIResponse<?> responseDTO = new APIResponse<>();
            responseDTO.setStatus(ApiResponseStatus.FAILED.toString());
            responseDTO.setErrors(Collections.singletonList(new ErrorDto("UserName", "UserName Already Present!")));
        return new ResponseEntity<>(responseDTO, HttpStatus.CONFLICT);

        }
        
    }
    

    @PostMapping("/login")
    public ResponseEntity<APIResponse<?>> login(@RequestBody JwtAuthRequest request) throws Exception {
        String jwt = null;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            jwt = jwtTokenUtil.generateToken(userDetails);
           
        } catch (UsernameNotFoundException ex) {
            throw new UsernameNotFoundException("Invalid username or password!");
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid username or password!");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while authenticating!", e);
        }

        APIResponse<String> responseDTO = APIResponse.<String>builder()
                                        .status(ApiResponseStatus.SUCCESS.toString())
                                        .results(jwt)
                                        .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);


    }
    

    
    
    
}
