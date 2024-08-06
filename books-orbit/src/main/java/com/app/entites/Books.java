package com.app.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookId;

	private String bookName;
	
	
	private String description;
	
	private Integer quantity;
	
	private double price;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private User sellerId;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
//	@OneToMany(mappedBy = "product", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
//	private List<CartItem> products = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "books", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<OrderItem> orderItems = new ArrayList<>();

}
