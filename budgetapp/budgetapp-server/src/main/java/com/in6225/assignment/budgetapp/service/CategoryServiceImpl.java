package com.in6225.assignment.budgetapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in6225.assignment.budgetapp.entity.Category;
import com.in6225.assignment.budgetapp.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
    private CategoryRepository categoryRepository;
    
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
    
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
	
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
    
    public Category updateCategory(Category category) {
		return categoryRepository.save(category);
    }
    
    public String deleteCategory(Long categoryId) {
    	categoryRepository.deleteById(categoryId);
    	return "Entry has been deleted!";
    }
    
}


