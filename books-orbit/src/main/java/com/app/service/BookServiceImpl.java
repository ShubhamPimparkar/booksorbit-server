package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entites.Books;
import com.app.entites.FavouriteBooks;
import com.app.entites.User;
import com.app.repository.BookRepo;
import com.app.repository.FavRepo;
import com.app.repository.UserRepo;
import com.app.response.ApiResponse;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private FavRepo favRepo;
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
	@Override
	public ApiResponse addToFav(Long bid, Long uid) {
		User user = userRepo.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        Books book = bookRepo.findById(bid).orElseThrow(() -> new RuntimeException("Book not found"));

        FavouriteBooks favoriteBook = new FavouriteBooks();
        favoriteBook.setUser(user);
        favoriteBook.setBook(book);
        favRepo.save(favoriteBook);
    		
		return new ApiResponse("Added to fav");
	}
	@Override
	public Books getBookName(String bname) {
		Books book = bookRepo.findBooksByBookName(bname);
		return book;
	}

}
