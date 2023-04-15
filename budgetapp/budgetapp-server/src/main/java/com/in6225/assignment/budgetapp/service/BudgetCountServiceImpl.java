package com.in6225.assignment.budgetapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in6225.assignment.budgetapp.entity.User;
import com.in6225.assignment.budgetapp.repository.BudgetRepository;


@Service
public class BudgetCountServiceImpl implements BudgetCountService {

	@Autowired
    private BudgetRepository budgetRepository;
    
    public Double getCategoryTypeSum(User user, char categoryId) {
    	return budgetRepository.findCategoryTypeTotalSum(user, categoryId);
    }
    
    public Double getCategoryNameSum(User user, String categoryName) {
    	return budgetRepository.findCategoryNameTotalSum(user, categoryName);
    }
}


