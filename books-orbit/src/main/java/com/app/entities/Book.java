package com.app.entities;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

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
@Table(name = "Books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	
	@Column(name = "b_name")
	private String bookName;
		
	@OneToMany(mappedBy = "bookId")
	private List<Comment> comments;
	
	@Column(name = "auth_name")
	private String author_Name;
	
	@Column(name = "quantity")
	private int quantity;
	
	@CreatedDate
	@Column(name = "added_date")
	private LocalDate added_date;
	
	@ManyToOne
	@JoinColumn(name = "category")
	private Category choosenCat;
	
	@Column(name = "image")
	private Blob image;
	
	@ManyToOne
	@JoinColumn(name = "s_id")
	private Seller sellerId;
	
	@Column(name = "rating",length = 5)
	private int rating;
	
	@ManyToOne
	@JoinColumn(name = "Cartitem_id")
	private CartItem cartItemID;
}

