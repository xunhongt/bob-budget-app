package com.in6225.assignment.budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in6225.assignment.budgetapp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category findByCategoryName(String categoryName);
}