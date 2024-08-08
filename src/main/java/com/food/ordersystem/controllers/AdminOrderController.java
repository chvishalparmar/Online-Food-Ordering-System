package com.food.ordersystem.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.ordersystem.dto.APIResponse;
import com.food.ordersystem.enitites.Orders;
import com.food.ordersystem.enums.ApiResponseStatus;
import com.food.ordersystem.services.CURDOrderService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/admin/order/")
@RequiredArgsConstructor
public class AdminOrderController {

    private final CURDOrderService curdOrderService;

    @GetMapping("get/{status}")
    public ResponseEntity<APIResponse<?>> getOrders(@PathVariable String status) {
        List<Orders> orders = curdOrderService.getOrderStatusForAdmin(status);

        APIResponse<List<Orders>> response = APIResponse.<List<Orders>>builder()
                                            .status(ApiResponseStatus.SUCCESS.toString())
                                            .results(orders)
                                            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/quantity/{status}")
    public ResponseEntity<APIResponse<?>> getOrdersQuantity(@PathVariable String status) {
        HashMap<String , Integer> orders = curdOrderService.getOrdersQunatity(status);

        APIResponse<HashMap<String , Integer>> response = APIResponse.<HashMap<String , Integer>>builder()
                                            .status(ApiResponseStatus.SUCCESS.toString())
                                            .results(orders)
                                            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update/status/{id}")
    public ResponseEntity<APIResponse<?>> updateStatus(@PathVariable Long id, @RequestParam String status) {
       Orders order = curdOrderService.updateStatus(id, status);

       APIResponse<Orders> response = APIResponse.<Orders>builder()
                                            .status(ApiResponseStatus.SUCCESS.toString())
                                            .results(order)
                                            .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
        
    }
    
    
}
