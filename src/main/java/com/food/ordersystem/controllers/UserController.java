package com.food.ordersystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.ordersystem.dto.APIResponse;
import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.User;
import com.food.ordersystem.services.UserService;
import com.food.ordersystem.vaildationgroup.UpdateUserGroup;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public static final String SUCCESS = "Success";

    public static final String FAILED = "Failed";

    @GetMapping("details")
    public ResponseEntity<APIResponse<?>> getDetails()  {
     String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userDto = new UserDto();
        userDto.setUsername(username);

        User user = userService.getUser(userDto);

         APIResponse<User> responseDTO = APIResponse.<User>builder()
                                        .status(SUCCESS)
                                        .results(user)
                                        .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        
        
    }

    @PutMapping("update")
    public ResponseEntity<APIResponse<?>> updateUser( @Validated(UpdateUserGroup.class) @RequestBody UserDto userDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userDto.setUsername(username);
        User user = userService.updateUser(userDto);
        APIResponse<User> responseDTO = APIResponse.<User>builder()
                                        .status(SUCCESS)
                                        .results(user)
                                        .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @DeleteMapping("delete")
    public ResponseEntity<APIResponse<?>> deleteUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
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
