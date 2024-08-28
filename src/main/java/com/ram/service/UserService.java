package com.ram.service;

import com.ram.exception.UserException;
import com.ram.model.User;

public interface UserService 
{
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	
	
}
