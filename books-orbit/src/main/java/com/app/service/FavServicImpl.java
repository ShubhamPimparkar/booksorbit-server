package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entites.Books;
import com.app.entites.FavouriteBooks;
import com.app.repository.FavRepo;

@Service
@Transactional
public class FavServicImpl implements FavService {

	
	@Autowired
	private FavRepo favRepo;
	
	
	@Override
	public List<Books> getFavoriteBooksByUser(Long userId) {
		
		 return favRepo.findAll().stream()
		            .filter(fb -> fb.getUser().getUserId().equals(userId))
		            .map(FavouriteBooks::getBook)
		            .collect(Collectors.toList());
	}

}
