package com.app.service;

import java.util.List;

import com.app.entites.Books;
import com.app.response.ApiResponse;

public interface BookService {

	ApiResponse addBook(Books book);

	List<Books> getBooks();

	ApiResponse delBook(Long bid);

	Books getBook(Long bid);

	ApiResponse addToFav(Long bid, Long uid);

	Books getBookName(String bname);
	
}
