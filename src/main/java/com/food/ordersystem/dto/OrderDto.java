package com.food.ordersystem.dto;

import java.time.LocalDate;
import java.util.List;

import com.food.ordersystem.enums.DeliveryStatus;
import com.food.ordersystem.vaildationgroup.CreateGroup;
import com.food.ordersystem.vaildationgroup.UpdateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {

    @NotNull(message = "order Number is mandatory" , groups = {UpdateGroup.class})
    private Long orderNumber; // auto genrated by jpa 
    private LocalDate orderDate; //auto genrated by jpa 
    private Double totalPrice;
    @NotBlank(message = "Address is mandatory" , groups = {CreateGroup.class , UpdateGroup.class})
    private String deliveryAddress;
    private DeliveryStatus deliveryStatus;
    private UserDto user; // add user in controller
    private List<OrderItemDTO> orderItems;
    
}
