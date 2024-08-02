package com.food.ordersystem.dto;

import org.hibernate.sql.Update;
import com.food.ordersystem.vaildationgroup.CreateGroup;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DishDto {

    private Long id;
    
    @NotBlank(message = "Name is mandatory" , groups = {CreateGroup.class})
    private String name;
    
    @NotBlank(message = "description is mandatory" , groups = {CreateGroup.class ,Update.class})
    private String description;
    
    
    @Positive(groups = {CreateGroup.class ,Update.class})
    @NotNull(message = "Price is mandatory" , groups = {CreateGroup.class ,Update.class})
    private Double price;
    
    @NotBlank(message = "cuisine is mandatory" , groups = {CreateGroup.class , Update.class} )
    @Enumerated(EnumType.STRING)
    private String cuisine;
    
    @NotNull(message = "Availability is mandatory" , groups = {CreateGroup.class , Update.class})
    private Boolean availability;
    
    
}
