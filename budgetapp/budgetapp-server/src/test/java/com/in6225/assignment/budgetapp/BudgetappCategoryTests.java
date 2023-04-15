package com.in6225.assignment.budgetapp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.in6225.assignment.budgetapp.entity.Category;
import com.in6225.assignment.budgetapp.service.CategoryServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BudgetappCategoryTests {

	@Autowired
	ApplicationContext context;
	
	@Test
    @Order(15) 
	public void add_category() {
		
		CategoryServiceImpl categoryServiceImpl=context.getBean(CategoryServiceImpl.class);
	
		Category category = new Category();
		category.setCategoryName("Test");
		category.setCategoryType('E');

		categoryServiceImpl.addCategory(category);
	}
	
	@Test
    @Order(16) 
	public void get_categories() {
		
		CategoryServiceImpl categoryServiceImpl=context.getBean(CategoryServiceImpl.class);
	
		categoryServiceImpl.getCategories();
	}
	
	@Test
    @Order(17) 
	public void get_category() {
		
		CategoryServiceImpl categoryServiceImpl=context.getBean(CategoryServiceImpl.class);
	
		long categoryId = 1;
		categoryServiceImpl.getCategory(categoryId);
	}
	
	@Test
    @Order(18) 
	public void get_category_by_name() {
		
		CategoryServiceImpl categoryServiceImpl=context.getBean(CategoryServiceImpl.class);
	
		String categoryName = "HOUSING";
		categoryServiceImpl.getCategoryByName(categoryName);
	}
	
	@Test
    @Order(19) 
	public void update_category() {
		
		CategoryServiceImpl categoryServiceImpl=context.getBean(CategoryServiceImpl.class);
	
		String categoryName = "Test";
		Category category = categoryServiceImpl.getCategoryByName(categoryName);
		
		category.setCategoryName("Test 2");
		category.setCategoryType('I');
		
		categoryServiceImpl.updateCategory(category);
	}
	
	@Test
    @Order(20) 
	public void delete_category() {
		
		CategoryServiceImpl categoryServiceImpl=context.getBean(CategoryServiceImpl.class);
		
		long categoryId = 1;
		categoryServiceImpl.deleteCategory(categoryId);
	}
	
}
