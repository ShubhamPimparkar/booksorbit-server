package com.app.service;

import com.app.entites.User;
import com.app.response.ApiResponse;


public interface UserService {

	ApiResponse addUser(User user);
	
}
