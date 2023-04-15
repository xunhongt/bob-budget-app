package com.in6225.assignment.budgetapp.controller;

import com.in6225.assignment.budgetapp.exception.ResourceConflictException;

import com.in6225.assignment.budgetapp.entity.User;
import com.in6225.assignment.budgetapp.service.UserService;
import com.in6225.assignment.budgetapp.security.WebSecurityConfig;

import com.in6225.assignment.budgetapp.controller.dto.AuthResponse;
import com.in6225.assignment.budgetapp.controller.dto.LoginRequest;
import com.in6225.assignment.budgetapp.controller.dto.SignUpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
 * BudgetApp - Authentication Controller
 * 
 * This controller class handles any authentication related requests and routes to the User Service class
 * 
 * It contains the following methods:
 * 
 * 	POST methods:
 * 		- login --> Login user to application
 * 		- signup --> Creates a new user in the User database
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private UserService userService;
 
	@Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
    	
       User loginUser = userService.validUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        
       if (loginUser != null ) {
            User user = userService.getUser(loginRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(user.getUsername(), user.getRole()));
        }
       
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        if (userService.userExists(signUpRequest.getUsername())) {
            throw new ResourceConflictException(String.format("Username %s is already been used", signUpRequest.getUsername()));
        }
        if (userService.emailExists(signUpRequest.getEmail())) {
            throw new ResourceConflictException(String.format("Email %s is already been used", signUpRequest.getEmail()));
        }

        User user = userService.addUser(createUser(signUpRequest));
        
        return new AuthResponse(user.getUsername(), user.getRole());
    }

    private User createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(WebSecurityConfig.USER);
        return user;
    }
}