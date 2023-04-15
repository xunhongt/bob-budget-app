package com.in6225.assignment.budgetapp.service;

import java.util.List;

import com.in6225.assignment.budgetapp.entity.Category;

public interface CategoryService {

    Category addCategory(Category category);
    
    Category getCategory(Long CategoryId);
    
    Category getCategoryByName(String CategoryName);
    
    List<Category> getCategories();
    
    Category updateCategory(Category category);
    
    String deleteCategory(Long CategoryId);
}


