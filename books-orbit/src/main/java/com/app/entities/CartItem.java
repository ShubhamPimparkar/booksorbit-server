package com.app.entities;

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
@Table(name = "CartItem")
public class CartItem {

	@Id
//	@Generated(strategy=GenerationType.IDENTITY)
	private Long cartItemId;
	
	@Column(name="Quantity")
	private int quantity;
	
	@Column(name = "Price")
	private float price;
	
	@Column(name="BookId")
	private Book bookId;
}
