package com.app.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private Long orderId;
	private String email;
	private String orderStatus;
	private LocalDate orderDate;
	private Double totalAmount;
}
