package com.food.ordersystem.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.ordersystem.dto.APIResponse;
import com.food.ordersystem.dto.BillDto;
import com.food.ordersystem.dto.OrderDto;
import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.Orders;
import com.food.ordersystem.enums.ApiResponseStatus;
import com.food.ordersystem.services.BillService;
import com.food.ordersystem.services.CURDOrderService;
import com.food.ordersystem.vaildationgroup.CreateGroup;
import com.food.ordersystem.vaildationgroup.UpdateGroup;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserOrderController {
    
    private final CURDOrderService curdOrderService;

    private final BillService billService;
    
    @PostMapping("order")
    public ResponseEntity<APIResponse<?>> createOrder(@Validated(CreateGroup.class) @RequestBody OrderDto orderDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        orderDto.setUser(userDto);
        Orders orderDto_CreateOrder = curdOrderService.createOrder(orderDto);
      
       APIResponse<Orders> response = APIResponse.<Orders>builder()
                                      .status(ApiResponseStatus.SUCCESS.toString())
                                      .results(orderDto_CreateOrder)
                                      .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("get/status/{status}")
    public ResponseEntity<APIResponse<?>> getOrderStatus(@PathVariable String status) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Orders> order = curdOrderService.getOrderStatus(status, username);

        APIResponse<List<Orders>> response = APIResponse.<List<Orders>>builder()
                                            .status(ApiResponseStatus.SUCCESS.toString())
                                            .results(order)
                                            .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<APIResponse<?>> getById(@PathVariable Long id) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderNumber(id);
        Orders order = curdOrderService.getOrder(orderDto);
        APIResponse<Orders> response = APIResponse.<Orders>builder()
                                        .status(ApiResponseStatus.SUCCESS.toString())
                                        .results(order)
                                        .build();
        
       return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/get/bill/{id}")
    public ResponseEntity<APIResponse<?>> getBill(@PathVariable Long id) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderNumber(id);
        BillDto dto = billService.getBill(orderDto);

        APIResponse<BillDto> response = APIResponse.<BillDto>builder()
                                        .status(ApiResponseStatus.SUCCESS.toString())
                                        .results(dto)
                                        .build();
        
       return new ResponseEntity<>(response, HttpStatus.OK);

    }
    
    
    
    @PutMapping("update/order")
    public ResponseEntity<APIResponse<?>> updateAddress(@Validated(UpdateGroup.class) @RequestBody OrderDto orderDto) {
        Orders order = curdOrderService.updateOrder(orderDto);
        APIResponse<Orders> response = APIResponse.<Orders>builder()
                                       .status(ApiResponseStatus.SUCCESS.toString())
                                       .results(order)
                                       .build();

       return new ResponseEntity<>(response, HttpStatus.OK);
       
    }

    @PutMapping("cancel/{id}")
    public ResponseEntity<APIResponse<?>> cancelOrder(@PathVariable Long id){

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderNumber(id);
        Orders order = curdOrderService.cancelOrder(orderDto);
        APIResponse<Orders> response = APIResponse.<Orders>builder()
                                       .status(ApiResponseStatus.SUCCESS.toString())
                                       .results(order)
                                       .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
}
