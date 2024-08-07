package com.food.ordersystem.services;

import java.util.List;

import com.food.ordersystem.dto.ReviewDto;
import com.food.ordersystem.enitites.Review;

public interface ReviewService {
    List<Review> getReviewByUser(ReviewDto reviewDto);
    List<Review> getReviewOfDish(ReviewDto reviewDto);
    Review saveReview(ReviewDto reviewDto);
    
}
