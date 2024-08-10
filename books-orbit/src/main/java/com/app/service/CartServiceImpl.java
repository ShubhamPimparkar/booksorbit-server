package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dtos.BookDTO2;
import com.app.dtos.CartDTO;
import com.app.dtos.CartItemDTO;
import com.app.entites.Books;
import com.app.entites.Cart;
import com.app.entites.CartItem;
import com.app.entites.User;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repository.BookRepo;
import com.app.repository.CartItemRepo;
import com.app.repository.CartRepo;
import com.app.repository.UserRepo;
import com.app.response.ApiResponse;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CartItemRepo cartItemRepo;

	@Override
	public void addCart(Cart cart) {
		cartRepo.save(cart);
	}

	@Override
	public CartDTO addBookToCart(Long cartId, Long bookId, Integer quantity) {
		Cart cart = cartRepo.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

		Books product = bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "productId", bookId));
		System.out.println("Book FOund-----------------");

		CartItem cartItem = cartItemRepo.findCartItemByCartAndBook(cart,product);
		if (cartItem != null) {
//			throw new APIException("Product " + product.getBookName() + " already exists in the cart");

			
			cartItem.setQuantity(cartItem.getQuantity() +quantity);
			

			cartItemRepo.save(cartItem);

			product.setQuantity(product.getQuantity() - quantity);

			cart.setTotalPrice((cart.getTotalPrice() + cartItem.getBookPrice()) * quantity);
			cartRepo.save(cart);

			CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

			List<BookDTO2> productDTOs = cart.getCartItems().stream()
					.map(p -> modelMapper.map(p.getBook(), BookDTO2.class)).collect(Collectors.toList());

			cartDTO.setProducts(productDTOs);

			return cartDTO;
			
			
		}else {
			
		

		CartItem newCartItem = new CartItem();
		newCartItem.setCart(cart);
		System.out.println("Cartitem FOund-----------------");

		
		if (product.getQuantity() == 0) {
			throw new APIException(product.getBookName() + " is not available");
		}

		if (product.getQuantity() < quantity) {
			throw new APIException("Please, make an order of the " + product.getBookName()
					+ " less than or equal to the quantity " + product.getQuantity() + ".");
		}

//		CartItem newCartItem = new CartItem();

		newCartItem.setBook(product);
		newCartItem.setCart(cart);
		newCartItem.setQuantity(quantity);
		newCartItem.setBookPrice(product.getPrice());

		cartItemRepo.save(newCartItem);

		product.setQuantity(product.getQuantity() - quantity);

		cart.setTotalPrice((cart.getTotalPrice() + newCartItem.getBookPrice()) * quantity);
		cartRepo.save(cart);

		CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

		List<BookDTO2> productDTOs = cart.getCartItems().stream()
				.map(p -> modelMapper.map(p.getBook(), BookDTO2.class)).collect(Collectors.toList());

		cartDTO.setProducts(productDTOs);

		return cartDTO;
		}
	}

	@Override
	public List<CartDTO> getAllCarts() {
		List<Cart> carts = cartRepo.findAll();

		if (carts.size() == 0) {
			throw new APIException("No cart exists");
		}

		List<CartDTO> cartDTOs = carts.stream().map(cart -> {
			CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

			List<BookDTO2> products = cart.getCartItems().stream()
					.map(p -> modelMapper.map(p.getBook(), BookDTO2.class)).collect(Collectors.toList());

			cartDTO.setProducts(products);

			return cartDTO;

		}).collect(Collectors.toList());

		return cartDTOs;

	}

	@Override
	public String deleteProductFromCart(Long cartId, Long bookId) {
		Cart cart = cartRepo.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));
		Books book1 = bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Books", "bookId", bookId));
		

		CartItem cartItem = cartItemRepo.findByBookAndCart(book1, cart);
		
		
		if (cartItem == null) {
			throw new ResourceNotFoundException("Books", "bookId", bookId);
		}

		cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getBookPrice() * cartItem.getQuantity()));

		cartRepo.save(cart);
		Books book = cartItem.getBook();
		book.setQuantity(book.getQuantity() + cartItem.getQuantity());
		bookRepo.save(book);
		cartItemRepo.deleteCartItemByBookAndCart(book1,cart);
	

		return "Product " + cartItem.getBook().getBookName() + " removed from the cart !!!";
	}

	@Override
	public CartDTO getCart(String emailId) {
			
		User user = userRepo.findByEmail(emailId);
		Cart cart = cartRepo.findByUser(user);

		if (cart == null) {
			throw new ResourceNotFoundException("User", "emailId", emailId);
		}

		CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
		
		List<BookDTO2> books = cart.getCartItems().stream()
				.map(p -> modelMapper.map(p.getBook(), BookDTO2.class)).collect(Collectors.toList());

		cartDTO.setProducts(books);

		return cartDTO;
	
	}

	@Override
	public CartDTO getCartById(Long cartId) {
		Cart cart = cartRepo.findById(cartId).get();
		CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
		List<BookDTO2> books = cart.getCartItems().stream()
				.map(p -> modelMapper.map(p.getBook(), BookDTO2.class)).collect(Collectors.toList());

		cartDTO.setProducts(books);
		return cartDTO;
	}

	@Override
	public List<CartItemDTO> getCartItemsById(Long cartId) {
		Cart cart = cartRepo.findById(cartId).get();
		List<CartItem> list = cart.getCartItems();
		
		List<CartItemDTO> cartItems = list.stream()
				.map(p -> {
					CartItemDTO cdtos = modelMapper.map(p, CartItemDTO.class);
					
					BookDTO2 products = modelMapper.map(p.getBook(), BookDTO2.class);

					cdtos.setProduct(products);

					return cdtos;
				
				}).collect(Collectors.toList());

		
		return cartItems;
	}

	@Override
	public ApiResponse updateItems(Long cartItemId, Long bookid, Integer quant) {
		Books book = bookRepo.findById(bookid).get();
		CartItem item = cartItemRepo.findByCartItemIdAndBook(cartItemId,book);
		item.setQuantity(item.getQuantity()+quant);
		book.setQuantity(book.getQuantity() + quant);
		bookRepo.save(book);
		Cart cart = item.getCart();
		cart.setTotalPrice(cart.getTotalPrice() + (book.getPrice()*quant));
		cartRepo.save(cart);
		cartItemRepo.save(item);
		return new ApiResponse("Quantity +/- Updated");
	}

	
}
