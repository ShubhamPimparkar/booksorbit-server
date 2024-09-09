package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dtos.UserDTO;
import com.app.entites.User;
import com.app.repository.UserRepo;
import com.app.response.ApiResponse;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ApiResponse addUser(User user) {

		userRepo.save(user);
		return new ApiResponse("User Inserted");
	}

	@Override
	public List<User> getUsers() {
		List<User> list = userRepo.findAll();
		return list;
	}

	@Override
	public ApiResponse delUser(Long uid) {
		userRepo.deleteById(uid);
		return new ApiResponse("User Deleted");
	}

	@Override
	public User getById(Long userId) {

		return userRepo.findById(userId).get();
	}

	@Override
	public UserDTO getUser(String email, String password) {
		User user = userRepo.findUserByEmail(email);
		if (user != null) {
			passwordEncoder.matches(password, user.getPassword());
			UserDTO udto = modelMapper.map(user, UserDTO.class);
			return udto;
		} else {
			return null;
		}

	}

}
