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
@Table(name = "Address")
public class Address {
	@Id
//	@Generated(strategy=GenerationType.IDENTITY)
	private Long addressId;
	
	@Column(name = "Area")
	private String Area;
	
	@Column(name = "City")
	private String City;
	
	@Column(name = "State")
	private String State;
	
	@Column(name = "Pincode")
	private int pincode;
	
}
