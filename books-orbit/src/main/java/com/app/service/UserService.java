package com.app.service;

import java.util.List;

import com.app.entites.User;
import com.app.response.ApiResponse;


public interface UserService {

	ApiResponse addUser(User user);

	List<User> getUsers();

	ApiResponse delUser(Long uid);
	
}
