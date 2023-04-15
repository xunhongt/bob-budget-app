package com.in6225.assignment.budgetapp.service;

import java.util.List;

import com.in6225.assignment.budgetapp.entity.User;

public interface UserService {

    User addUser(User user);

    User getUser(String username);
    
    List<User> getUsers();
    
    boolean userExists(String username);
    
    boolean emailExists(String email);
    
    User updateUser(User user);
    
    String deleteUser(String username);
    
    User validUsernameAndPassword(String username, String password);
}


