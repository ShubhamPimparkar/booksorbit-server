package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.Category;
import com.app.repository.CategoryRepository;
import com.app.response.ApiResponse;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse addNewCategory(Category category) {
//		Category category = mapper.map(categoryDto, Category.class);
        
		categoryRepository.save(category);
		return new ApiResponse("Category Added");
}

	@Override
	public List<Category> getCategory() {
		List<Category> list=categoryRepository.findAll();
		return list;
	}

	@Override
	public ApiResponse deleteCategory(Long id) {
		categoryRepository.deleteById(id);
		return new ApiResponse("Deleted");
	}
}
