package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entites.Cart;

public interface CartRepo extends JpaRepository<Cart, Long> {

}
