package com.in6225.assignment.budgetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.in6225.assignment.budgetapp.entity.Budget;
import com.in6225.assignment.budgetapp.entity.Category;
import com.in6225.assignment.budgetapp.entity.User;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
  List<Budget> findByUser(User user);
  //List<Budget> findByUsernameAndCategoryId(String username, long categoryId);
  
  public List<Budget> findByUserAndCategory(User user, Category category);
  
  @Query("""
  		SELECT SUM(amount) 
  		FROM Budget b, Category c 
  		WHERE b.category = c
  		AND c.categoryType = :categoryType
  		AND b.user = :user
  		""")
  public Double findCategoryTypeTotalSum(
		  @Param("user") User user, 
		  @Param("categoryType") char categoryType);
  
  @Query("""
	  		SELECT SUM(amount) 
	  		FROM Budget b, Category c 
	  		WHERE b.category = c
	  		AND c.categoryName = :categoryName
	  		AND b.user = :user
	  		""")
	  public Double findCategoryNameTotalSum(
			  @Param("user") User user, 
			  @Param("categoryName") String categoryName);
  
}

