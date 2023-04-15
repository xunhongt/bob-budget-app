package com.in6225.assignment.budgetapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in6225.assignment.budgetapp.entity.Budget;
import com.in6225.assignment.budgetapp.entity.Category;
import com.in6225.assignment.budgetapp.entity.User;
import com.in6225.assignment.budgetapp.repository.BudgetRepository;


@Service
public class BudgetServiceImpl implements BudgetService {

	@Autowired
    private BudgetRepository budgetRepository;
    
    public Budget addBudget(Budget budget) {
        return budgetRepository.save(budget);
    }
    
    public Budget getBudget(Long budgetId) {
        return budgetRepository.findById(budgetId).orElse(null);
        		//.orElseThrow(() -> new ResourceNotFoundException("Not found Budget with id = " + budgetId));
    }
	
    public List<Budget> getBudgets(User user) {
        return budgetRepository.findByUser(user);
    }
    
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }
    
    public List<Budget> getBudgetsByCategory(User user, Category category) {
        return budgetRepository.findByUserAndCategory(user, category);
    }
    
    public Budget updateBudget(Budget budget) {
		return budgetRepository.save(budget);
    }
    
    public String deleteBudget(Long budgetId) {
    	budgetRepository.deleteById(budgetId);
    	return "Entry has been deleted!";
    }
}


