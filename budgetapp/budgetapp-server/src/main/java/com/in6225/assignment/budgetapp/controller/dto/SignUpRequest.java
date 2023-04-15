package com.in6225.assignment.budgetapp.controller.dto;

/*
 * BudgetApp - SignUpRequest Data Transfer Object (DTO)
 * 
 * This DTO class is used to transfer data between: 
 * 
 * 	Source: HTTP Request Body (POST /signup)
 * 
 * 	Destination: User Service (addUser Method)
 * 
 */

public class SignUpRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String name;
    private String email;
    
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}