package com.food.ordersystem.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ReviewDto {
     
    private Long id;
    @NotBlank(message = "Comment is mandatory" , groups = {ReviewGroup.class})
    private String comment;
    @Max(5)
    @Min(1)
    @NotNull(message = "Rating is mandatory" , groups = {RatingGroup.class,ReviewGroup.class})
    private Double rating;
    @NotBlank(message = "Dish is mandatory" , groups = {ReviewGroup.class, RatingGroup.class})
    private String dishName;
    //@NotBlank(message = "UserName is mandatory" , groups = {ReviewGroup.class , RatingGroup.class})
    private String userName;

    public interface ReviewGroup {
        
    }

    public interface RatingGroup {
    
        
    }
}
