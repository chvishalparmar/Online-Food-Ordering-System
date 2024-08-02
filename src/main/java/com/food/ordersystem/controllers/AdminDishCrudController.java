package com.food.ordersystem.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.ordersystem.dto.APIResponse;
import com.food.ordersystem.dto.DishDto;
import com.food.ordersystem.dto.ErrorDto;
import com.food.ordersystem.enitites.Dish;
import com.food.ordersystem.services.DishCrudService;
import com.food.ordersystem.vaildationgroup.CreateGroup;
import com.food.ordersystem.vaildationgroup.UpdateGroup;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/admin/dish/")
@RequiredArgsConstructor
public class AdminDishCrudController {

    private final DishCrudService dishCrudService;

    public static final String SUCCESS = "Success";

    public static final String FAILED = "Failed";

    @GetMapping("details/{name}")
    public ResponseEntity<APIResponse<?>> getMethodName(@PathVariable String name) {
        DishDto dishDto = new DishDto();
        dishDto.setName(name);

        Dish dish = dishCrudService.getDish(dishDto);

        APIResponse<Dish> responseDTO = APIResponse.<Dish>builder()
                                        .status(SUCCESS)
                                        .results(dish)
                                        .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    

    @PostMapping("add")
    public ResponseEntity<APIResponse<?>> addDish(@Validated(CreateGroup.class) @RequestBody DishDto dishDto) {
       
        Dish addDish = dishCrudService.saveDish(dishDto);

        if(addDish != null){
            APIResponse<Dish> responseDto = APIResponse.<Dish>builder()
                                        .status(SUCCESS)
                                        .results(addDish)
                                        .build();
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        }else{

             APIResponse<?> responseDTO = new APIResponse<>();
            responseDTO.setStatus(FAILED);
            responseDTO.setErrors(Collections.singletonList(new ErrorDto("Name", "Dish Already Present!")));
        return new ResponseEntity<>(responseDTO, HttpStatus.CONFLICT);
        }
   
    }

    @PutMapping("update/{name}")
    public ResponseEntity<APIResponse<?>> updateDish(@PathVariable String name, @Validated(UpdateGroup.class) @RequestBody DishDto dishDto) {
       dishDto.setName(name);

       Dish dish = dishCrudService.upadateDish(dishDto);

       APIResponse<Dish> responseDTO = APIResponse.<Dish>builder()
                                       .status(SUCCESS)
                                       .results(dish)
                                      .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("delete/{name}")
    public ResponseEntity<APIResponse<?>> deleteDish(@PathVariable String name){

        DishDto dishDto = new DishDto();
        dishDto.setName(name);

        boolean check = dishCrudService.deleteDish(dishDto);

        if(check == true){
            APIResponse<String> responseDTO =  new APIResponse<>();
            responseDTO.setStatus(SUCCESS);
            responseDTO.setResults("Dish Deleted Successfully!");
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }else{
            APIResponse<?> responseDTO = APIResponse.<String>builder()
            .status(FAILED)
            .results(new String("Dish Not Deleted !"))
            .build();
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    
    }
    
    

    
}
