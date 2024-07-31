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
@Table(name = "Books")
public class Book {

	@Id
	//@Generated(strategy=GenerationType.IDENTITY)
	private Long bookId;
	
	@Column(name = "b_name")
	private String bookName;
		
	@Column(name = "c_Id")
	private Comment comment_id;
	
	@Column(name = "b_name")
	private String author_Name;
	
	@Column(name = "b_qantity")
	private int quantity;
	
	@Column(name = "Added_date")
	private LocalDate added_date;
	
	@Column(name = "image")
	private Blob image;
	
	@Column(name = "s_id")
	private Seller sellerId;
	
	@Column(name = "rating")
	private int rating;
}
