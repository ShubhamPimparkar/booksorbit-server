package com.app.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "favouritebooks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FavouriteBooks {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long favId;
	 	
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "book_id")
	    private Books book;

}
