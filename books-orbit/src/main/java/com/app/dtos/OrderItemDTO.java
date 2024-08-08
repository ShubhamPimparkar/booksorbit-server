package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
	private Long orderItemId;
	private BookDTO2 book;
	private Integer quantity;
	
	private double orderedProductPrice;

}
