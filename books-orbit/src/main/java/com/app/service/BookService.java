package com.app.service;

import java.util.List;

import com.app.entites.Books;
import com.app.response.ApiResponse;

public interface BookService {
	
	ApiResponse addNewBook(Books book);

	List<Books> getAllBooks();

	ApiResponse deleteBookById(Long bookId);	
}
