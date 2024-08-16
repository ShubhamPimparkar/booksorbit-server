package com.app.entites;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private String password;

	private String city;
	private String state;
	private String country;

	@Enumerated(EnumType.STRING)
	private RoleEnum role;

//	@OneToOne(cascade = CascadeType.REMOVE,orphanRemoval = true)
//	@JoinColumn(name = "address_id")
//	private Address address;

	@JsonIgnore
	@OneToMany(mappedBy = "seller", cascade = { CascadeType.REMOVE, CascadeType.PERSIST,
			CascadeType.MERGE }, orphanRemoval = true)
	private List<Books> book;
	
	@JsonIgnore
	@OneToOne(mappedBy = "user", cascade = { CascadeType.REMOVE, CascadeType.PERSIST,
			CascadeType.MERGE }, orphanRemoval = true)
	private Cart cart;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_favorite_books", joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "book_id"))
	private Set<Books> favoriteBooks;
	

}
