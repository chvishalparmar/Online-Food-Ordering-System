package com.food.ordersystem.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.food.ordersystem.dto.APIResponse;
import com.food.ordersystem.dto.ReviewDto;
import com.food.ordersystem.dto.ReviewDto.RatingGroup;
import com.food.ordersystem.dto.ReviewDto.ReviewGroup;
import com.food.ordersystem.enitites.Review;
import com.food.ordersystem.enums.ApiResponseStatus;
import com.food.ordersystem.services.ReviewService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequiredArgsConstructor
@RestController
public class ReviewDishController {

    private final ReviewService reviewService;

    @GetMapping("/user/get/review")
    public ResponseEntity<APIResponse<?>> getReviewByUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ReviewDto reviewDto = new ReviewDto(); 
        reviewDto.setUserName(username);
        List<Review> reviews = reviewService.getReviewByUser(reviewDto);

        APIResponse<List<Review>> response = APIResponse.<List<Review>>builder()
                                            .status(ApiResponseStatus.SUCCESS.toString())
                                            .results(reviews)
                                            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/public/get/review/dish")
    public ResponseEntity<APIResponse<?>> getReviewByDish(@RequestParam String dish) {
        ReviewDto reviewDto = new ReviewDto(); 
        reviewDto.setDishName(dish);
        List<Review> reviews = reviewService.getReviewOfDish(reviewDto);
        APIResponse<List<Review>> response = APIResponse.<List<Review>>builder()
                                            .status(ApiResponseStatus.SUCCESS.toString())
                                            .results(reviews)
                                            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/user/add/review")
    public ResponseEntity<APIResponse<?>> addReviewForDish(@Validated(ReviewGroup.class)@RequestBody ReviewDto reviewDto) {
       String username = SecurityContextHolder.getContext().getAuthentication().getName();
       reviewDto.setUserName(username);
       Review review = reviewService.saveReview(reviewDto);
       APIResponse<Review> response = APIResponse.<Review>builder()
                                            .status(ApiResponseStatus.SUCCESS.toString())
                                            .results(review)
                                            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/user/add/rating")
    public ResponseEntity<APIResponse<?>> addRatingForDish(@Validated(RatingGroup.class)@RequestBody ReviewDto reviewDto) {
       String username = SecurityContextHolder.getContext().getAuthentication().getName();
       reviewDto.setUserName(username);
       Review review = reviewService.saveReview(reviewDto);
       APIResponse<Review> response = APIResponse.<Review>builder()
                                            .status(ApiResponseStatus.SUCCESS.toString())
                                            .results(review)
                                            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    
    
}
