package com.in6225.assignment.budgetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import com.in6225.assignment.budgetapp.entity.User;
import com.in6225.assignment.budgetapp.service.BudgetCountService;
import com.in6225.assignment.budgetapp.service.UserService;

/*
 * BudgetApp - Budget Count Controller
 * 
 * This controller class handles any Budget Count requests and routes to the Budget Count Service class
 * 
 * It contains the following methods:
 * 
 * GET methods:
 * 		- getSavings --> Returns User's savings from BudgetApp (Income - Expenditure)
 * 		- getExpenditure --> Returns User's Expenditure from BudgetApp
 * 		- getIncome --> Returns User's Income from BudgetApp
 * 		- getCategorySum --> Returns User's expenditure/income based on Category
 * 	
 */

@RestController
@RequestMapping("/")

public class BudgetCountController {

		@Autowired
	    private BudgetCountService budgetCountService;
			
		@Autowired
	    private UserService userService;
		
		@GetMapping("/budgetcount")
		public ResponseEntity<Double> getSavings(){
			
			//--- Get currently logged-in username ----
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
			
			User user = userService.getUser(currentPrincipalName);
			
			if (user != null) {
				Double expenditureSum = budgetCountService.getCategoryTypeSum(user, 'E');
				
				if (expenditureSum == null) {
					expenditureSum = 0.00;
				}
				
				Double incomeSum = budgetCountService.getCategoryTypeSum(user, 'I');
				
				if (incomeSum == null) {
					incomeSum = 0.00;
				}
				
				Double savings = incomeSum - expenditureSum;

				return new ResponseEntity<Double>(savings, HttpStatus.OK);
				
			} else {
				
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		@GetMapping("/budgetcount/expenditure")
		public ResponseEntity<Double> getExpenditure(){
			
			//--- Get currently logged-in username ----
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
			
			User user = userService.getUser(currentPrincipalName);
			
			if (user != null) {
				Double expenditureSum = budgetCountService.getCategoryTypeSum(user, 'E');	

				if (expenditureSum == null) {
					expenditureSum = 0.00;
				}
				
				return new ResponseEntity<Double>(expenditureSum, HttpStatus.OK);
				
			} else {
				
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
			}
		}
		
		@GetMapping("/budgetcount/income")
		public ResponseEntity<Double> getIncome(){
			
			//--- Get currently logged-in username ----
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
			
			User user = userService.getUser(currentPrincipalName);
			
			if (user != null) {
				Double incomeSum = budgetCountService.getCategoryTypeSum(user, 'I');	

				if (incomeSum == null) {
					incomeSum = 0.00;
				}
				
				return new ResponseEntity<Double>(incomeSum, HttpStatus.OK);
				
			} else {
				
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
			}
		}
		
		@GetMapping("/budgetcount/type/{categoryName}")
		public ResponseEntity<Double> getCategorySum(@PathVariable("categoryName") String categoryName){
			
			//--- Get currently logged-in username ----
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
			
			User user = userService.getUser(currentPrincipalName);
			
			if (user != null) {
				Double categorySum = budgetCountService.getCategoryNameSum(user, categoryName);	
				
				if (categorySum == null) {
					categorySum = 0.00;
				}

				return new ResponseEntity<Double>(categorySum, HttpStatus.OK);
				
			} else {
				
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
			}
		}
}
