package com.in6225.assignment.budgetapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import com.in6225.assignment.budgetapp.entity.Budget;
import com.in6225.assignment.budgetapp.entity.Category;
import com.in6225.assignment.budgetapp.entity.User;

import com.in6225.assignment.budgetapp.exception.ResourceNotFoundException;

import com.in6225.assignment.budgetapp.service.BudgetService;
import com.in6225.assignment.budgetapp.service.CategoryService;
import com.in6225.assignment.budgetapp.service.UserService;

import com.in6225.assignment.budgetapp.controller.dto.AddBudgetDto;
import com.in6225.assignment.budgetapp.controller.dto.GetBudgetDto;

/*
 * BudgetApp - Budget Controller
 * 
 * This controller class handles any budget related requests and routes to the Budget Service class
 * 
 * It contains the following methods:
 * 
 * GET methods:
 * 		- getBudgets --> Get list of budgets submitted by currently logged in user
 * 		- getBudget --> Get budget details based on budget ID
 * 		- getAllBudgets --> Get all budgets in BudgetApp
 * 		- getBudgetByCategory --> Get list of budgets of a specific Catgory submitted by currently logged in user
 * 	
 * POST methods: 
 * 		- addBudget --> Add a new budget entry into BudgetApp
 * 
 * PUT methods:
 * 		- updateBudget --> Update budget details in BudgetApp
 * 
 * DELETE methods:
 * 		- deleteBudget --> delete budget details based on budgetID
 */

@RestController
@RequestMapping("/")

public class BudgetController {

		@Autowired
	    private BudgetService budgetService;
		
		@Autowired
	    private CategoryService categoryService;
		
		@Autowired
	    private UserService userService;
		
		@GetMapping("/budgets")
		public ResponseEntity<List<GetBudgetDto>> getBudgets(){
			
			//--- Get currently logged-in username ----
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
			
			List<Budget> budgets = new ArrayList<Budget>();
			
			User user = userService.getUser(currentPrincipalName);
			
			if (user != null) {
		    	budgets = budgetService.getBudgets(user);
		    	
				if (budgets.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				
				List<GetBudgetDto> getBudgetsDto = budgets.stream()
														.map( i -> new GetBudgetDto(
																i.getBudgetId(),
																i.getCategory().getCategoryName(),
																i.getUser().getUsername(),
																i.getAmount(),
																i.getDescription(),
																i.getDateCreated(),
																i.getDateUpdated()
																))
														.collect(Collectors.toList());

				return new ResponseEntity<List<GetBudgetDto>>(getBudgetsDto, HttpStatus.OK);
				
			} else {
				
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
			}
		}
	   		
		@GetMapping("/budget/{id}")
		public ResponseEntity<GetBudgetDto> getbudget(@PathVariable("id") Long budgetId) {
			
			Budget budget = budgetService.getBudget(budgetId);

			if (budget == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			GetBudgetDto getBudgetDto = new GetBudgetDto(
												budget.getBudgetId(),
												budget.getCategory().getCategoryName(),
												budget.getUser().getUsername(),
												budget.getAmount(),
												budget.getDescription(),
												budget.getDateCreated(),
												budget.getDateUpdated()
											);
			
			return new ResponseEntity<GetBudgetDto>(getBudgetDto, HttpStatus.OK);
		}
		
		@GetMapping("/allbudgets")
		public ResponseEntity<List<GetBudgetDto>> getAllBudgets() {
			
			List<Budget> budgets = new ArrayList<Budget>();
	    	
	    	budgets = budgetService.getAllBudgets();

			if (budgets.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			List<GetBudgetDto> getBudgetsDto = budgets.stream()
													.map( i -> new GetBudgetDto(
															i.getBudgetId(),
															i.getCategory().getCategoryName(),
															i.getUser().getUsername(),
															i.getAmount(),
															i.getDescription(),
															i.getDateCreated(),
															i.getDateUpdated()
															))
													.collect(Collectors.toList());
			
			return new ResponseEntity<List<GetBudgetDto>>(getBudgetsDto, HttpStatus.OK);
		}
	
		@PostMapping("/budgets")
		public ResponseEntity<AddBudgetDto> addBudget(@RequestBody AddBudgetDto addBudgetDto) {
			
			//--- Get currently logged-in username ----
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
	 
			User user = userService.getUser(currentPrincipalName);
			
			Category category = categoryService.getCategoryByName(addBudgetDto.getCategoryName());
			
			
			if (user != null && category != null) {
				
				budgetService.addBudget(
						new Budget(
								user,
								category,
								addBudgetDto.getAmount(),
								addBudgetDto.getDescription()
						));
				
				return new ResponseEntity<>(addBudgetDto, HttpStatus.CREATED);
				
			} else if (user == null) {
				throw new ResourceNotFoundException("Username with id = " + currentPrincipalName + " not found!");
			} else {
				throw new ResourceNotFoundException("Category Name = " + addBudgetDto.getCategoryName() + " not found!");				
			}

		}
	
	    @GetMapping("/budgets/{categoryName}")
		public ResponseEntity<List<GetBudgetDto>> getBudgetsByCategory(@PathVariable("categoryName") String categoryName) {
			
			List<Budget> budgets = new ArrayList<Budget>();
			
			//--- Get currently logged-in username ----
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
			
			User user = userService.getUser(currentPrincipalName);
	
			Category category = categoryService.getCategoryByName(categoryName);
			
			if (user != null && category != null) {
				
				budgets = budgetService.getBudgetsByCategory(user, category);
				
				if (budgets.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				
				List<GetBudgetDto> getBudgetsDto = budgets.stream()
														.map( i -> new GetBudgetDto(
																i.getBudgetId(),
																i.getCategory().getCategoryName(),
																i.getUser().getUsername(),
																i.getAmount(),
																i.getDescription(),
																i.getDateCreated(),
																i.getDateUpdated()
																))
														.collect(Collectors.toList());
				
				return new ResponseEntity<List<GetBudgetDto>>(getBudgetsDto, HttpStatus.OK);
				
			} else if (user == null) {
				
				throw new ResourceNotFoundException("Username with id = " + currentPrincipalName + " not found!");
			
			} else {
				
				throw new ResourceNotFoundException("Category Name = " + categoryName + " not found!");				
			
			}
		}
	    
	    @PutMapping("/budget/{id}")
		public ResponseEntity<AddBudgetDto> updateBudget(@RequestBody AddBudgetDto addBudgetDto, @PathVariable("id") long budgetId) {
			
			Budget existingBudget = budgetService.getBudget(budgetId);
			Category category = categoryService.getCategoryByName(addBudgetDto.getCategoryName());
			
			if (existingBudget != null) {
				
				existingBudget.setAmount(addBudgetDto.getAmount());
				existingBudget.setCategory(category);
				existingBudget.setDescription(addBudgetDto.getDescription());
				
				budgetService.updateBudget(existingBudget);
				
				return new ResponseEntity<>(addBudgetDto, HttpStatus.OK);
				
			} else {

				throw new ResourceNotFoundException("Budget with id = " + budgetId + " not found!");
				
			}
		}

	    @DeleteMapping("/budget/{id}")
		public ResponseEntity<HttpStatus> deleteBudget(@PathVariable("id") Long budgetId) {

			budgetService.deleteBudget(budgetId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
}
