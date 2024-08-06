package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BookDTO {
	private Long bookId;
	private String bookName;
	private String description;
	private double price;
	private Integer quantity;
	
}
