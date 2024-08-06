package com.app.service;

import com.app.entites.Books;
import com.app.response.ApiResponse;

public interface BookService {

	ApiResponse addBook(Books book);
	
}
