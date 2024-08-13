package com.app.service;

import java.util.List;

import com.app.entites.Books;

public interface FavService {

	List<Books> getFavoriteBooksByUser(Long userId);

}
