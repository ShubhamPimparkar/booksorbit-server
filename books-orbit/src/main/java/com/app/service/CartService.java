package com.app.service;

import java.util.List;

import com.app.dtos.CartDTO;
import com.app.dtos.CartItemDTO;
import com.app.entites.Cart;

public interface CartService {
	void addCart(Cart cart);

	CartDTO addBookToCart(Long cartId, Long bookId, Integer quantity);

	List<CartDTO> getAllCarts();

	String deleteProductFromCart(Long cartId, Long bookId);

	CartDTO getCart(String emailId);

	CartDTO getCartById(Long cartId);

	List<CartItemDTO> getCartItemsById(Long cartId);
	
}
