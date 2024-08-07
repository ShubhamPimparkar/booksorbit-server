package com.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class BookDTO2 {
	@JsonProperty(access = Access.READ_ONLY)
	private Long bookId;

	private String bookName;

	private String description;

	private Integer quantity;

	private double price;

}
