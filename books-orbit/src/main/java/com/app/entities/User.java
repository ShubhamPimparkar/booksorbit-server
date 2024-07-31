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
@Table(name="User")
public class User {
	@Id
//	@Generated(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name = "u_name")
	private String userName;
	
	@Column(name = "u_email",unique = true)
	private String userEmail;
	
	@Column(name = "u_password")
	private String userPassword;
	
	@Column(name = "u_address_id")
	private String userAdressId;
	
}
