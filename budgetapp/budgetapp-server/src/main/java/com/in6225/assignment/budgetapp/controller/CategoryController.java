package com.in6225.assignment.budgetapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in6225.assignment.budgetapp.entity.Category;
import com.in6225.assignment.budgetapp.exception.ResourceNotFoundException;
import com.in6225.assignment.budgetapp.service.CategoryService;

/*
 * BudgetApp - Category Controller
 * 
 * This controller class handles any category related requests and routes to the Category Service class
 * 
 * It contains the following methods:
 * 
 * GET methods:
 * 		- getCategories --> Get list of categories in BudgetApp
 * 		- getCategory --> Get Category in BudgetApp
 *  	- getCategoryCount --> Returns total number of categories in BudgetApp
 * 	
 * POST methods: 
 * 		- addCategory --> Add a new category entry into BudgetApp
 * 
 * PUT methods:
 * 		- updateCategory --> Update category in BudgetApp
 * 
 * DELETE methods:
 * 		- deleteCategory --> delete category in BudgetApp
 */

@RestController
@RequestMapping("/")

public class CategoryController {

		@Autowired
	    private CategoryService categoryService;

		@GetMapping("/categories")
		public ResponseEntity<List<Category>> getCategories() {
			
			List<Category> categories = new ArrayList<Category>();
	    	
	    	categories = categoryService.getCategories();

			if (categories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(categories, HttpStatus.OK);
		}
		
		@GetMapping("/totalcategories")
		public ResponseEntity<Integer> getCategoryCount() {
			
			Integer categoryCount = categoryService.getCategories().size();
			
			return new ResponseEntity<>(categoryCount, HttpStatus.OK);
		}
		
		@GetMapping("/categories/{id}")
		public ResponseEntity<Category> getCategories(@PathVariable("id") Long categoryId) {
			
			Category category = categoryService.getCategory(categoryId);

			if (category == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(category, HttpStatus.OK);
		}
		
	
		@PostMapping("/categories")
		public ResponseEntity<Category> addCategory(@RequestBody Category category) {
			
			Category newCategory = categoryService.addCategory(category);
			return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
		}
		
	    @PutMapping("/categories/{id}")
		public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("id") long categoryId) {
			
			Category existingCategory = categoryService.getCategory(categoryId);

			if (existingCategory != null) {        
				existingCategory.setCategoryName(category.getCategoryName());
				existingCategory.setCategoryType(category.getCategoryType());
				return new ResponseEntity<>(categoryService.updateCategory(existingCategory), HttpStatus.OK);
				
			} else {
				//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				throw new ResourceNotFoundException("Category with id = " + categoryId + " not found!");		
			}
		}

	    @DeleteMapping("/categories/{id}")
		public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") Long categoryId) {

			categoryService.deleteCategory(categoryId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
	    
	
}
