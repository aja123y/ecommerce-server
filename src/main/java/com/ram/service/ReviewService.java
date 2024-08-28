package com.ram.service;

import java.util.List;

import com.ram.exception.ProductException;
import com.ram.model.Review;
import com.ram.model.User;
import com.ram.request.ReviewRequest;

public interface ReviewService {
	
	public Review CreateReview(ReviewRequest req,User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);

}
