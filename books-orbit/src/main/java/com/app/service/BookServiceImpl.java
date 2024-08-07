package com.app.service;

import java.util.List;

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
	@Override
	public List<Books> getBooks() {
		
		return bookRepo.findAll();
	}
	@Override
	public ApiResponse delBook(Long bid) {
		bookRepo.deleteById(bid);
		return new ApiResponse("Book Deleted");
	}
	@Override
	public Books getBook(Long bid) {
		Books book = bookRepo.findById(bid).get();
		return book;
	}

}
