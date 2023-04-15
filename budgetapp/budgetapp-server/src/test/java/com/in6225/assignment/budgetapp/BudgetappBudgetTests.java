package com.in6225.assignment.budgetapp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.in6225.assignment.budgetapp.entity.Budget;
import com.in6225.assignment.budgetapp.entity.Category;
import com.in6225.assignment.budgetapp.entity.User;
import com.in6225.assignment.budgetapp.service.BudgetServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BudgetappBudgetTests {

	@Autowired
	ApplicationContext context;
	
	@Test
    @Order(8) 
	public void add_budget() {
		
		BudgetServiceImpl budgetServiceImpl=context.getBean(BudgetServiceImpl.class);
		
		Budget budget = new Budget();
		budget.setAmount(4.55);
		
		User user = new User();
		user.setUsername("testuser");
		
		Category category = new Category();
		category.setCategoryId((long) 1);
		
		budget.setCategory(category);
		budget.setUser(user);
		budget.setDescription("This is a test case");

		budgetServiceImpl.addBudget(budget);
	}

	@Test
    @Order(9) 
	public void get_budgets() {
		
		BudgetServiceImpl budgetServiceImpl=context.getBean(BudgetServiceImpl.class);
		
		User user = new User();
		user.setUsername("testuser");

		budgetServiceImpl.getBudgets(user);
	}
	
	@Test
    @Order(10) 
	public void get_all_budgets() {
		
		BudgetServiceImpl budgetServiceImpl=context.getBean(BudgetServiceImpl.class);
		
		budgetServiceImpl.getAllBudgets();
	}
	
	@Test
    @Order(11) 
	public void get_budget() {
		
		BudgetServiceImpl budgetServiceImpl=context.getBean(BudgetServiceImpl.class);
		
		long budgetId = 1;

		budgetServiceImpl.getBudget(budgetId);
	}
	
	@Test
    @Order(12) 
	public void get_budgets_by_category() {
		
		BudgetServiceImpl budgetServiceImpl=context.getBean(BudgetServiceImpl.class);
	
		User user = new User();
		user.setUsername("testuser");
		
		Category category = new Category();
		category.setCategoryId((long) 1);
			
		budgetServiceImpl.getBudgetsByCategory(user, category);
	}
	
	@Test
    @Order(13) 
	public void update_budget() {
		
		BudgetServiceImpl budgetServiceImpl=context.getBean(BudgetServiceImpl.class);
		
		long budgetId = 1;
		
		Budget budget = budgetServiceImpl.getBudget(budgetId);
		budget.setAmount(10.55);
		
		User user = new User();
		user.setUsername("testuser");
		
		Category category = new Category();
		category.setCategoryId((long) 2);
		
		budget.setCategory(category);
		budget.setUser(user);
		budget.setDescription("This is a test case #2");

		budgetServiceImpl.updateBudget(budget);
	}
	
	@Test
    @Order(14)
	public void delete_budget() {
		
		BudgetServiceImpl budgetServiceImpl=context.getBean(BudgetServiceImpl.class);
		
		long budgetId = 1;
		budgetServiceImpl.deleteBudget(budgetId);
	}
}
