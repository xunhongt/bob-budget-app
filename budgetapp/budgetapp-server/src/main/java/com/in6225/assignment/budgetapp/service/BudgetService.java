package com.in6225.assignment.budgetapp.service;

import java.util.List;

import com.in6225.assignment.budgetapp.entity.Budget;
import com.in6225.assignment.budgetapp.entity.Category;
import com.in6225.assignment.budgetapp.entity.User;

public interface BudgetService {

    Budget addBudget(Budget budget);
   
    Budget getBudget(Long budgetId);
    
    List<Budget> getBudgets(User user);
    
    List<Budget> getAllBudgets();
    
    List<Budget> getBudgetsByCategory(User user, Category category);
    
    Budget updateBudget(Budget budget);
    
    String deleteBudget(Long budgetId);
}


