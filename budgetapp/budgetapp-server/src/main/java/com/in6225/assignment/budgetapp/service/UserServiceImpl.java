package com.in6225.assignment.budgetapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in6225.assignment.budgetapp.entity.User;
import com.in6225.assignment.budgetapp.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
    
    public User addUser(User user) {
        return userRepository.save(user);
    }
    
    public User getUser(String username) {
        return userRepository.findById(username).orElse(null);
    }
    
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }
    
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
    
    public User updateUser(User user) {
		return userRepository.save(user);
    }
    
    public String deleteUser(String username) {
    	userRepository.deleteById(username);
    	return "Entry has been deleted!";
    }
    
    public User validUsernameAndPassword(String username, String password) {
        return userRepository.findById(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(null);
    }
    
}


