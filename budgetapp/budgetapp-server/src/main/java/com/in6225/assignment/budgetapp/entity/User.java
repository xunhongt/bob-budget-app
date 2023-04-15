package com.in6225.assignment.budgetapp.entity;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name = "username", unique=true)
	private String username;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", unique=true, nullable = false)
	private String email;
	
	@Column(name = "role", nullable = false)
	private String role;
	
    @OneToMany(mappedBy = "user")
    List<Budget> budgets;
 
    
    public User(String username, String firstName, String lastName, String password, String email, String role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    
    public User() {
    }
    
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [Username=" + username + ", firstName=" + firstName + ", "
				+ "lastName=" + lastName + ", " + "password=" + password + 
				", " + "email=" + email + ", " + "role=" + role + "]";
	}
}
