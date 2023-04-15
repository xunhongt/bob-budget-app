package com.in6225.assignment.budgetapp.controller.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GetBudgetDto {
	
    private Long budgetId;
    private String categoryName;
    private String username;
    private Double amount;
    private String description;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateCreated;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateUpdated;
    
    public GetBudgetDto(Long budgetId, String categoryName, String username, Double amount, String description, Date dateCreated, Date dateUpdated) {
        this.budgetId = budgetId;
    	this.categoryName = categoryName;
        this.username = username;
        this.amount = amount;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }
    
	public Long getBudgetId() {
		return budgetId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public String getUsername() {
		return username;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public Date getDateUpdated() {
		return dateUpdated;
	}

}