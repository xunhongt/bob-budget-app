package com.in6225.assignment.budgetapp.controller.dto;

/*
 * BudgetApp - LoginRequest Data Transfer Object (DTO)
 * 
 * This DTO class is used to transfer data between: 
 * 
 * 	Source: HTTP Request Body (POST /authenticate)
 * 
 * 	Destination: User Service (validateUsernameAndPassword)
 * 
 */

public class LoginRequest {

    private String username;
    private String password;
    
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}