package com.food.ordersystem.dto;

import com.food.ordersystem.enitites.Orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data 
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {

    private Long billId;
    private String Name;
    private Orders order;

}
