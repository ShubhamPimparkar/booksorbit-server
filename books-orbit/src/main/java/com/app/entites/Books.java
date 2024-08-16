package com.app.entites;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	private String authorName;
	@Column(length = 500)
	private String description;
	private String imgUrl;
	
	private Integer quantity; 
	
	private double price;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private User seller;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToMany(mappedBy = "favoriteBooks")
    private Set<User> users;
	
	@JsonIgnore
	@OneToMany(mappedBy = "book", cascade = { CascadeType.REMOVE, CascadeType.PERSIST,
			CascadeType.MERGE }, orphanRemoval = true)
	private List<FavouriteBooks> favs;
	
	@JsonIgnore
	@OneToMany(mappedBy = "book", cascade = { CascadeType.REMOVE, CascadeType.PERSIST,
			CascadeType.MERGE }, orphanRemoval = true)
	private List<CartItem> cartitems;
	
}
