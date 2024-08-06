package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entites.Books;
import com.app.repository.BookRepo;
import com.app.response.ApiResponse;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;
	@Override
	public ApiResponse addBook(Books book) {
		bookRepo.save(book);
		return new ApiResponse("Book Inserted");
	}

}
