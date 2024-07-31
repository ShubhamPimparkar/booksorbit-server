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
@Table(name="Seller")
public class Seller {

	@Id
	//@Generated(strategy=GenerationType.IDENTITY)
	private Long sellerId;

	@Column(name="s_name")
	private String name;
	
	@Column(name="s_email")
	private String sellerEmail;
	
	@Column(name="password")
	private String password;
	
	@Column(name="addressId")
	private Address addressId;
	
	
}
