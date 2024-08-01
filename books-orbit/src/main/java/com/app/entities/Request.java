package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Request")
public class Request {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long requestId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userId;
	
	@Column(name = "book_name")
	
	private String bookName;
	@Column(name = "auth_name")
	private String authName;
	
}
