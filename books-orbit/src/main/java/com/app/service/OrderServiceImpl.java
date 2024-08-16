package com.app.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entites.Order;
import com.app.repository.OrderRepo;
import com.app.response.ApiResponse;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;
	@Override
	public ApiResponse placeOrder(String emailId, Double total, String status) {
		Order order = new Order();
		order.setEmail(emailId);
		order.setOrderDate(LocalDate.now());
		order.setOrderStatus(status);
		order.setTotalAmount(total);
		orderRepo.save(order);
		return new ApiResponse("Order Placed");
	}

}
