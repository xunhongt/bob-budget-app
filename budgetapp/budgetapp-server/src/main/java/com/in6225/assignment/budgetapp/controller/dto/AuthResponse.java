package com.in6225.assignment.budgetapp.controller.dto;

/*
 * BudgetApp - AuthResponse Data Transfer Object (DTO)
 * 
 * This DTO class is used to transfer data between: 
 * 
 * 	Source: User Service (addUser Method)
 * 
 * 	Destination: HTTP Response Body (POST /signup)
 * 
 */

public record AuthResponse(String username, String role) {
}