package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.UserDTO;
import com.app.entites.Address;
import com.app.entites.User;
import com.app.response.ApiResponse;
import com.app.service.AddressService;
import com.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addService;

	@PostMapping
	private ResponseEntity<ApiResponse> addUser(@RequestBody UserDTO userDTO) {

		User user = convertToEntity(userDTO);
		ApiResponse res = userService.addUser(user);
		return ResponseEntity.ok(res);

	}
	
	private User convertToEntity(UserDTO userDTO) {
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setMobileNumber(userDTO.getMobileNumber());
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());
		if (userDTO.getAddress() != null) {
			Address address = new Address();
			address.setStreet(userDTO.getAddress().getStreet());
			address.setBuildingName(userDTO.getAddress().getBuildingName());
			address.setCity(userDTO.getAddress().getCity());
			address.setState(userDTO.getAddress().getState());
			address.setPincode(userDTO.getAddress().getPincode());
			address.setCountry(userDTO.getAddress().getCountry());
			user.setAddress(address);
			addService.saveAdd(address);
		}
		return user;
	}

}