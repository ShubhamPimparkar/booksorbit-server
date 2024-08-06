package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dtos.BookDTO;
import com.app.entites.Books;
import com.app.repository.BookRepository;
import com.app.response.ApiResponse;

@Service
@Transactional

public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepo;
	
	
	@Override
	public ApiResponse addNewBook(Books books) {
		 bookRepo.save(books);
		 return new ApiResponse("New Book Added");
	}


	@Override
	public List<Books> getAllBooks() {
		List<Books> list= bookRepo.findAll();
		return list;
	}


	@Override
	public ApiResponse deleteBookById(Long bookId) {
		bookRepo.deleteById(bookId);
		return new ApiResponse("Deleted");
	}
}
