package com.food.ordersystem.serviceImp;

import java.util.List;
import org.springframework.stereotype.Service;

import com.food.ordersystem.dto.DishDto;
import com.food.ordersystem.dto.ReviewDto;
import com.food.ordersystem.dto.UserDto;
import com.food.ordersystem.enitites.Dish;
import com.food.ordersystem.enitites.OrderItem;
import com.food.ordersystem.enitites.Orders;
import com.food.ordersystem.enitites.Review;
import com.food.ordersystem.enitites.User;
import com.food.ordersystem.enums.DeliveryStatus;
import com.food.ordersystem.exceptions.DishNotFoundException;
import com.food.ordersystem.repo.ReviewRepo;
import com.food.ordersystem.services.CURDOrderService;
import com.food.ordersystem.services.DishService;
import com.food.ordersystem.services.ReviewService;
import com.food.ordersystem.services.UserService;
import com.food.ordersystem.utils.ValueMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewServiceImp implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final DishService dishService;
    private final UserService userService;
    private final ValueMapper valueMapper;
    private final CURDOrderService curdOrderService;

    

    @Override
    public Review saveReview(ReviewDto reviewDto) {

      // find User 
      User user = getUser(reviewDto.getUserName());

      // find Dish
      Dish dish = getDish(reviewDto.getDishName());

      boolean check_User_Order_Dish = false;

       // get User delivered Orders
      List<Orders> orders = curdOrderService.getOrderStatus(DeliveryStatus.DELIVERED.toString(), reviewDto.getUserName());
 
      //check User order dish or not
      for(Orders item: orders){

          for(OrderItem orderItem : item.getOrderItems()){
            if(orderItem.getItemName().equalsIgnoreCase(dish.getName())){
                check_User_Order_Dish = true;
                break;
            }
          }
      }

      if(check_User_Order_Dish == false){
        throw new DishNotFoundException("You are Trying Review Dish, Which is not Order or Delivered To You! ");
      }
      Review review = valueMapper.ReviewDto_To_Review(reviewDto);

      review.setUser(user);
      review.setDish(dish);
      review.addReviewToDish(dish);
      return reviewRepo.save(review);
      
    }

    private User getUser(String userName){
        UserDto UserDto = new UserDto();
        UserDto.setUsername(userName);
        return userService.getUser(UserDto);
    }

    private Dish getDish(String dishName){
        DishDto dishDto = new DishDto();
        dishDto.setName(dishName);
        return dishService.getDish(dishDto);
    }

    @Override
    public List<Review> getReviewByUser(ReviewDto reviewDto) {
        User user = getUser(reviewDto.getUserName());
        return reviewRepo.findByUser(user);
    }

    @Override
    public List<Review> getReviewOfDish(ReviewDto reviewDto) {
       Dish dish = getDish(reviewDto.getDishName());
       return reviewRepo.findByDish(dish);
    }
    
}
