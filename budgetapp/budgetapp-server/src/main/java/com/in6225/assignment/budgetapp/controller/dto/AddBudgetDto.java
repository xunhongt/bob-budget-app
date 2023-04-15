package com.in6225.assignment.budgetapp.controller.dto;

/*
 * BudgetApp - AddBudget Data Transfer Object (DTO)
 * 
 * This DTO class is used to transfer data between: 
 * 
 * 	Source: HTTP Request Body (POST /budgets)
 * 
 * 	Destination: Budget Service (addBudget Method)
 * 
 */

public class AddBudgetDto {
	
    private String categoryName;
    private Double amount;
    private String description;    
    
	public String getCategoryName() {
		return categoryName;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public String getDescription() {
		return description;
	}
}