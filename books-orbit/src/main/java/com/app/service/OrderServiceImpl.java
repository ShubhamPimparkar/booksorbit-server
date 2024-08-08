package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dtos.OrderDTO;
import com.app.dtos.OrderItemDTO;
import com.app.entites.Books;
import com.app.entites.Cart;
import com.app.entites.CartItem;
import com.app.entites.Order;
import com.app.entites.OrderItem;
import com.app.entites.Payment;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repository.CartItemRepo;
import com.app.repository.CartRepo;
import com.app.repository.OrderItemRepo;
import com.app.repository.OrderRepo;
import com.app.repository.PaymentRepo;
import com.app.repository.UserRepo;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	public UserRepo userRepo;

	@Autowired
	public CartRepo cartRepo;

	@Autowired
	public OrderRepo orderRepo;

	@Autowired
	private PaymentRepo paymentRepo;

	@Autowired
	public OrderItemRepo orderItemRepo;

	@Autowired
	public CartItemRepo cartItemRepo;

	@Autowired
	public UserService userService;

	@Autowired
	public CartService cartService;

	@Autowired
	public ModelMapper modelMapper;

	@Override
	public OrderDTO placeOrder(String emailId, Long cartId, String paymentMethod) {

		Cart cart = cartRepo.findById(cartId).get();

		if (cart == null) {
			throw new ResourceNotFoundException("Cart", "cartId", cartId);
		}
		Order order = new Order();

		order.setEmail(emailId);
		order.setOrderDate(LocalDate.now());
		order.setTotalAmount(cart.getTotalPrice());
		
		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setPaymentMethod(paymentMethod);		
		payment = paymentRepo.save(payment);
		
		order.setPayment(payment);
		Order savedOrder = orderRepo.save(order);
		List<CartItem> cartItems = cart.getCartItems();
		if (cartItems.size() == 0) {
			throw new APIException("Cart is empty");
		}

		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();

			orderItem.setBooks(cartItem.getBook());
			orderItem.setQuantity(cartItem.getQuantity());

			orderItem.setBookPrice(cartItem.getBookPrice());
			orderItem.setOrder(savedOrder);

			orderItems.add(orderItem);
		}
		orderItems = orderItemRepo.saveAllAndFlush(orderItems);
		cart.getCartItems().forEach(item -> {
			int quantity = item.getQuantity();
			Books product = item.getBook();
			cartService.deleteProductFromCart(cartId, item.getBook().getBookId());
			product.setQuantity(product.getQuantity() - quantity);
		});

		OrderDTO orderDTO = modelMapper.map(savedOrder, OrderDTO.class);
		orderItems.forEach(item -> orderDTO.getOrderItems().add(modelMapper.map(item, OrderItemDTO.class)));
		return orderDTO;
	}
}
