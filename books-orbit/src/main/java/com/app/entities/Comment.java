package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
	
	@Id
	//@Generated(strategy=GenerationType.IDENTITY)
	private Long commentId;
	
	@Column(name="UserId")
	private User userId;
	
	@Column(name="Description")
	private String description;
}
