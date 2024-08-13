package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entites.FavouriteBooks;

public interface FavRepo extends JpaRepository<FavouriteBooks, Long> {

	

}
