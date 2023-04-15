package com.in6225.assignment.budgetapp.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

/*
 * Entity ties to the Database Table --> in this case Budget Table
 */

@Entity
@Table(name="budget")
public class Budget {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "budget_id", nullable = false)	
	private Long budgetId;

	//Require one to many mapping (multiple budget rows map to 1 row in user) 
	@ManyToOne
	@JoinColumn(name = "username", referencedColumnName="username", nullable = false)
	private User user;
	
	//Require one to many mapping (multiple budget rows map to 1 row in category) 
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	@CreationTimestamp
	@Column(name = "date_created", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@UpdateTimestamp
	@Column(name = "date_updated", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	@Column(name = "amount", nullable = false)
	private Double amount;
	
	@Column(name = "description")
	private String description;
	
    public Budget(User user, Category category, Double amount, String description) {
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }
	
    public Budget() {
    	
    }
	
	public Long getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateTime) {
		this.dateCreated = dateTime;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateTime) {
		this.dateUpdated = dateTime;
	}

	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Budget [budgetId=" + budgetId + ", user=" + user + ", "
				+ "category=" + category + ", " + "dateCreated=" + dateCreated + 
				", " + "dateUpdated=" + dateUpdated + ", " + "amount=" + amount + ", " 
				+ "description=" + description + "]";
	}
	
}

