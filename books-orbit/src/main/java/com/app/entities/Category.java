package com.app.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "Category")
public class Category {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cateId;
	
	@Column(name = "cat_name")
	private String catName;
	@Column(name = "cat_desc")
	private String catDesc;
	
	@OneToMany(mappedBy = "choosenCat")
	private List<Book> books;

}
