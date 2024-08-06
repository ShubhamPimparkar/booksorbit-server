package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.CategoryDTO;
import com.app.entites.Category;
import com.app.response.ApiResponse;
import com.app.service.CategoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	private ResponseEntity<ApiResponse> addNewCategory(@RequestBody Category category){
//		Category category=convertToEntity(categoryDTO);
		ApiResponse res=categoryService.addNewCategory(category);
		return ResponseEntity.ok(res);
	}
	
	@GetMapping
	private ResponseEntity<List<Category>> getCategory(){
		List<Category> list= categoryService.getCategory();
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
		ApiResponse res=categoryService.deleteCategory(id);
		return ResponseEntity.ok(res);
	}
	
		
	
	private Category convertToEntity(CategoryDTO categoryDTO) {
		Category category=new Category();
		category.setCategoryId(categoryDTO.getCategoryId());
		category.setCategoryName(categoryDTO.getCategoryName());
		return category;
	}
	
}
