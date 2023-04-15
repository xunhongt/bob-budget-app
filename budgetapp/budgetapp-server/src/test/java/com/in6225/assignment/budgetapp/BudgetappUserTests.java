package com.in6225.assignment.budgetapp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.in6225.assignment.budgetapp.entity.User;
import com.in6225.assignment.budgetapp.service.UserServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BudgetappUserTests {

	@Autowired
	ApplicationContext context;
	
	@Test
    @Order(1) 
	public void add_user() {
		
		UserServiceImpl userServiceImpl=context.getBean(UserServiceImpl.class);
		
		User user = new User();
		user.setUsername("testuser1");
		user.setPassword("P@ssw0rd123");
		user.setFirstName("Test User");
		user.setLastName("1");
		user.setEmail("test.user.1@test.com");
		user.setRole("USER");
		
		userServiceImpl.addUser(user);
	}
	
	@Test
    @Order(2) 
	public void get_users() {
		
		UserServiceImpl userServiceImpl=context.getBean(UserServiceImpl.class);
		userServiceImpl.getUsers();
	}

	@Test
    @Order(3) 
	public void get_user() {
		
		UserServiceImpl userServiceImpl=context.getBean(UserServiceImpl.class);
		
		String username = "testuser1";
		userServiceImpl.getUser(username);
	}
	
	@Test
    @Order(4) 
	public void update_user() {
		
		UserServiceImpl userServiceImpl=context.getBean(UserServiceImpl.class);
		
		User user = new User();
		user.setUsername("testuser1");
		user.setPassword("Passw0rd132");
		user.setFirstName("Test User");
		user.setLastName("2");
		user.setEmail("test.user.2@test.com");
		user.setRole("USER");
		
		userServiceImpl.addUser(user);
	}
	
	@Test
    @Order(5) 
	public void check_email() {
		
		UserServiceImpl userServiceImpl=context.getBean(UserServiceImpl.class);
		
		String email = "test.user.2@test.com";

		userServiceImpl.emailExists(email);
	}
	
	@Test
    @Order(6) 
	public void validate_credentials() {
		
		UserServiceImpl userServiceImpl=context.getBean(UserServiceImpl.class);
		
		String username = "testuser";
		String password = "P@ssw0rd123";

		userServiceImpl.validUsernameAndPassword(username, password);
	}
	
	@Test
    @Order(7)
	public void delete_user() {
		
		UserServiceImpl userServiceImpl=context.getBean(UserServiceImpl.class);
		
		String username = "testuser1";
		userServiceImpl.deleteUser(username);
	}
}
