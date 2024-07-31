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
@Table(name="Cart")
public class Cart {
	@Id
//	@Generated(strategy=GenerationType.IDENTITY)
	private Long cartId;
	
	@Column(name = "c_Id")
	private CartItem cartItemId;

	@Column(name="u_Id")
	private User userId;
	
}
