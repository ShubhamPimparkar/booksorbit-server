package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entites.Books;
import com.app.entites.Cart;
import com.app.entites.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long>{

	CartItem findCartItemByCart(Long cartId);
//	CartItem findCartItemByBookIdAndCart( Long bookId,Long cartId);


	CartItem findByBookAndCart(Books book1, Cart cart);


	void deleteCartItemByBookAndCart(Books book1, Cart cart);


//	CartItem findCartItemByCart(Cart cart);


	CartItem findCartItemByCartAndBook (Cart cart, Books product);


//	CartItem findByIdAndBook (Long cartItemId, Books book);
	
	CartItem findByCartItemIdAndBook(Long cartItemId, Books book);


	CartItem findByBook (Books book);



	
	

	
}
