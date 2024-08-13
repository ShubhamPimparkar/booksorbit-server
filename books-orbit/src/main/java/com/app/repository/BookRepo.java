package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entites.Books;

public interface BookRepo extends JpaRepository<Books, Long> {

	Books findBooksByBookName(String bname);

}
