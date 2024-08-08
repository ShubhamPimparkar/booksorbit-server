package com.app.service;

import com.app.dtos.OrderDTO;

public interface OrderService {

	OrderDTO placeOrder(String emailId, Long cartId, String paymentMethod);

}
