package com.in6225.assignment.budgetapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in6225.assignment.budgetapp.entity.User;
import com.in6225.assignment.budgetapp.exception.ResourceNotFoundException;
import com.in6225.assignment.budgetapp.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * BudgetApp - User Controller
 * 
 * This controller class handles any user related requests and routes to the User Service class
 * 
 * It contains the following methods:
 * 
 * GET methods:
 * 		- getUsers --> Get list of Users in BudgetApp
 * 		- getUser --> Get user details in BudgetApp
 *  	- getUserCount --> Returns total number of Users in BudgetApp
 * 
 * PUT methods:
 * 		- updateUser --> Update User details in BudgetApp
 * 
 * DELETE methods:
 * 		- deleteUser --> delete user in BudgetApp
 */

@RestController
@RequestMapping("/")

public class UserController {

		@Autowired
	    private UserService userService;
		
		@Autowired
	    private PasswordEncoder passwordEncoder;

		@GetMapping("/users")
		public ResponseEntity<List<User>> getUsers() {
			
			List<User> users = new ArrayList<User>();
	    	
	    	users = userService.getUsers();

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		
		@GetMapping("/totalusers")
		public ResponseEntity<Integer> getUserCount() {
			
			Integer userCount = userService.getUsers().size();
			
			return new ResponseEntity<>(userCount, HttpStatus.OK);
		}

		@GetMapping("/users/{id}")
		public ResponseEntity<User> getUser(@PathVariable("id") String username) {
			
			User user = userService.getUser(username);

			if (user == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(user, HttpStatus.OK);
		}

		@PutMapping("/users")		
		public ResponseEntity<User> updateUser(@RequestBody User user) {
			
			User existingUser = userService.getUser(user.getUsername());

			if (existingUser != null) {        
				//existingUser.setUsername(user.getUsername());
				existingUser.setFirstName(user.getFirstName());
				existingUser.setLastName(user.getLastName());
				existingUser.setEmail(user.getEmail());
				existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
				return new ResponseEntity<>(userService.updateUser(existingUser), HttpStatus.OK);
				
			} else {
				//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
				throw new ResourceNotFoundException("Username with id = " + user.getUsername() + " not found!");
			}
		}

		@DeleteMapping("/users/{id}")	
		public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String username) {

			userService.deleteUser(username);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	    
	
}
