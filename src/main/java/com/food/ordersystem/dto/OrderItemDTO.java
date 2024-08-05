package com.food.ordersystem.dto;

import org.hibernate.sql.Update;

import com.food.ordersystem.vaildationgroup.CreateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    @NotBlank(message = "Item Name is mandatory" , groups = {CreateGroup.class})
    private String itemName;
    @Positive(groups = {CreateGroup.class ,Update.class})
    @NotBlank(message = "quantity is mandatory" , groups = {CreateGroup.class})
    private Integer quantity;
    
}
