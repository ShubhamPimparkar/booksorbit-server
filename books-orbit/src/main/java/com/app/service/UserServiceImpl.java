package com.app.service;

import java.util.List;

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

	@Override
	public List<User> getUsers() {
		List<User> list= userRepo.findAll();
		return list;
	}

	@Override
	public ApiResponse delUser(Long uid) {
		userRepo.deleteById(uid);
		return new ApiResponse("User Deleted");
	}

}
