package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.BookDTO;
import com.app.entites.Books;
import com.app.entites.Category;
import com.app.entites.User;
import com.app.response.ApiResponse;
import com.app.service.BookService;
import com.app.service.CatService;
import com.app.service.UserService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private CatService catService;
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping
	private ResponseEntity<ApiResponse> addBook(@RequestBody BookDTO bookDto){	
		Books book = mapToEntity(bookDto);
		return ResponseEntity.ok(bookService.addBook(book));
	
	}



	private Books mapToEntity(BookDTO bookDto) {
		Books book = new Books();
		book.setBookName(bookDto.getBookName());
		book.setDescription(bookDto.getDescription());
		book.setQuantity(bookDto.getQuantity());
		book.setPrice(bookDto.getPrice());
		
		User user = userService.getById(bookDto.getSellerId().getUserId());
		Category cat = catService.getById(bookDto.getCategoryId().getCateId());
		book.setSeller(user);
		book.setCategory(cat);
		return book;
	}
	

}
