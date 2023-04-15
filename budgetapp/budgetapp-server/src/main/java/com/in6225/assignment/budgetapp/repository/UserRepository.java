package com.in6225.assignment.budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in6225.assignment.budgetapp.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	boolean existsByEmail(String email);
}

