package com.in6225.assignment.budgetapp.entity;

import java.util.List;

import jakarta.persistence.*;

/*
 * Entity ties to the Database Table --> in this case Budget Table
 */

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", nullable = false)
	private Long categoryId;
	
	@Column(name = "category_name", nullable = false)
	private String categoryName;
	
	@Column(name = "category_type", nullable = false)
	private char categoryType;
	
    @OneToMany(mappedBy = "category")
    List<Budget> budgets;
	
    public Category(String categoryName, char categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
    
    public Category() {
    }
    
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public char getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(char categoryType) {
		this.categoryType = categoryType;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + 
				", " + "categoryType=" + categoryType + "]";
	}
	
}

