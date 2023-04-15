package com.in6225.assignment.budgetapp.exception;

public class ResourceNotFoundException extends RuntimeException {

	  private static final long serialVersionUID = 1L;

	  public ResourceNotFoundException(String msg) {
	    super(msg);
	  }
	}
