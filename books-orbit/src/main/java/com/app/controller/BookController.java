package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entites.Books;
import com.app.response.ApiResponse;
import com.app.service.BookService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/books")
public class BookController {
	
	
	@Autowired
	private BookService bookService;
	
	@PostMapping
	private ResponseEntity<ApiResponse> addNewBook(@RequestBody Books book){
		ApiResponse res= bookService.addNewBook(book);
		return ResponseEntity.ok(res);
	}
	
	@GetMapping
	private List<Books> getAllBooks(){
		List<Books> list=bookService.getAllBooks();
		return list;
	}
	
	@DeleteMapping("/{bookId}")
	private ResponseEntity<ApiResponse> deleteBookById(@PathVariable Long bookId) {
		ApiResponse res= bookService.deleteBookById(bookId);
		return ResponseEntity.ok(res);
	}
	
	
	
}
