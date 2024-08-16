package com.app.service;

import com.app.response.ApiResponse;

public interface OrderService {

	ApiResponse placeOrder(String emailId, Double total, String status);

}
