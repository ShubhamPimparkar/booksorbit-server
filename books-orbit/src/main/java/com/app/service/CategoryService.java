package com.app.service;

import java.util.List;

import com.app.dtos.CategoryDTO;
import com.app.entites.Category;
import com.app.response.ApiResponse;

public interface CategoryService {
	
	ApiResponse addNewCategory(Category category);

	List<Category> getCategory();

	ApiResponse deleteCategory(Long id);	
}
