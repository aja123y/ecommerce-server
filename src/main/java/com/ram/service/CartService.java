package com.ram.service;

import com.ram.exception.ProductException;
import com.ram.model.Cart;
import com.ram.model.User;
import com.ram.request.AddItemRequest;

public interface CartService 
{
	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
}
