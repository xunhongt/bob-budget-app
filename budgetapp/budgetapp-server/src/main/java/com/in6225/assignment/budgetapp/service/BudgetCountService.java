package com.in6225.assignment.budgetapp.service;

import com.in6225.assignment.budgetapp.entity.User;

public interface BudgetCountService {

    Double getCategoryTypeSum(User user, char categoryType);
    
    Double getCategoryNameSum(User user, String categoryName);
}


