package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.response.ApiResponse;
import com.app.service.OrderService;


@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	public OrderService orderService;
	
	@PostMapping("/users/{emailId}/total/{total}/status/{status}")
	public ResponseEntity<?> orderProducts(@PathVariable Double total, @PathVariable String status,@PathVariable String emailId) {
		ApiResponse api = orderService.placeOrder(emailId, total, status);
		return new ResponseEntity<ApiResponse>(api, HttpStatus.CREATED);
	}

}
