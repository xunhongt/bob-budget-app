package com.in6225.assignment.budgetapp;


import com.in6225.assignment.budgetapp.entity.Category;
import com.in6225.assignment.budgetapp.entity.User;
import com.in6225.assignment.budgetapp.security.WebSecurityConfig;
import com.in6225.assignment.budgetapp.service.CategoryService;
import com.in6225.assignment.budgetapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/*
 * Database Initializer Class
 * - Upon application startup, the class will perform the 2 functions:
 * 		1. add 2 Users (admin & testuser) into the User Table
 *   	2. add a set of pre-defined categories into the Catagory Table 
 */

@Component
public class DatabaseInitializer implements CommandLineRunner {

	@Autowired
    private UserService userService;
    
	@Autowired
	private CategoryService categoryService;

    @Override
    public void run(String... args) {
        if (!userService.getUsers().isEmpty()) {
            return;
        }
        users.forEach(userService::addUser);
        categories.forEach(categoryService::addCategory);
        System.out.println("Database initialized");
    }
    
    private static final List<User> users = Arrays.asList(
    		
    		new User(
    				"admin",
    				"Admin",
    				"Test",
    				"$2a$12$x0q6JpX4vhYsXHQw12RSNu.sG8RGwNEewtvdCekkldkF8eV0vLg8a",
    				"admin@test.com",
    				WebSecurityConfig.ADMIN
    			),
    		
    		new User(
    				"testuser",
    				"User",
    				"Test",
    				"$2a$12$x0q6JpX4vhYsXHQw12RSNu.sG8RGwNEewtvdCekkldkF8eV0vLg8a",
    				"user@test.com",
    				WebSecurityConfig.USER
    			)
    );
	
    private static final List<Category> categories = Arrays.asList(
    		
    		new Category("HOUSING", 'E'),
    		new Category("TRANSPORT", 'E'),
    		new Category("FOOD", 'E'),
    		new Category("UTILITIES", 'E'),
    		new Category("CLOTHING", 'E'),
    		new Category("MEDICAL", 'E'),
    		new Category("INSURANCE", 'E'),
    		new Category("HOUSEHOLD_ITEMS", 'E'),
    		new Category("PERSONAL", 'E'),
    		new Category("DEBT", 'E'),
    		new Category("EDUCATION", 'E'),
    		new Category("ENTERTAINMENT", 'E'),
    		new Category("GIFTS", 'E'),
    		new Category("SALARY", 'I'),
    		new Category("SAVINGS", 'I')
    );
}