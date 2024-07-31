package com.app.entities;

import java.sql.Blob;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Order")
public class Order {

	@Id
	//@Generated(strategy=GenerationType.IDENTITY)
	private Long orderId;
	
	private Book bookId;
	private User userId;
	private Seller sellerId;
	private int quantity;
	private float price;
	private LocalDate orderDate;
}
