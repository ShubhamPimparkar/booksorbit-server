package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entites.User;
import com.app.repository.UserRepo;
import com.app.response.ApiResponse;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo; 
	
	@Override
	public ApiResponse addUser(User user) {
		
		userRepo.save(user);
		return new ApiResponse("User Inserted");
	}

}
