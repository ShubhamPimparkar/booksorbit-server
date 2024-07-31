package com.app.entities;

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
@Table(name = "Payment")
public class Payment {

	@Id
	//@Generated(strategy=GenerationType.IDENTITY)
	private Long payment_Id;
	
	private Long transaction_id;
	private User userId;
	private Order orderId;
	private String bankName;
	private String type;
	
}
