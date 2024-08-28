package com.ram.service;

import java.util.List;

import com.ram.exception.ProductException;
import com.ram.model.Rating;
import com.ram.model.User;
import com.ram.request.RatingRequest;

public interface RatingService 
{
	public Rating createRating(RatingRequest req, User user) throws ProductException;
	
	public List<Rating> getProductRating(Long productId);
	
	
}
