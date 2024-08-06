package com.food.ordersystem.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.food.ordersystem.dto.APIResponse;
import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.User;
import com.food.ordersystem.services.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    public static final String SUCCESS = "Success";

    public static final String FAILED = "Failed";


    @GetMapping("/start")
    public String getStart() {
        return new String("inside admin controller");
    }

    @GetMapping("details/{username}")
    public ResponseEntity<APIResponse<?>> getDetails(@PathVariable String username) {

        UserDto userDto = new UserDto();
        userDto.setUsername(username);

        User user = userService.getUser(userDto);

         APIResponse<User> responseDTO = APIResponse.<User>builder()
                                        .status(SUCCESS)
                                        .results(user)
                                        .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        
        
    }

    @DeleteMapping("delete/{username}")
    public ResponseEntity<APIResponse<?>> deleteUser(@PathVariable String username){
        UserDto userDto = new UserDto();
        userDto.setUsername(username);

        Boolean check = userService.deleteUser(userDto);

        if(check == true){
            APIResponse<String> responseDTO =  new APIResponse<>();
            responseDTO.setStatus(SUCCESS);
            responseDTO.setResults("User Deleted Successfully!");
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }else{
            APIResponse<?> responseDTO = APIResponse.<String>builder()
            .status(FAILED)
            .results(new String("User Not Deleted !"))
            .build();
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    
}
