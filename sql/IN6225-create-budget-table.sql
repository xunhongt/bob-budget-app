CREATE DATABASE IF NOT EXISTS budgetapp;
USE budgetapp;

CREATE TABLE BUDGET(
    budget_id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
	category_id INT,
	dateTime DATETIME,
    amount NUMERIC(10,2),
    description VARCHAR(255),
    PRIMARY KEY (budget_id),
	FOREIGN KEY (username) REFERENCES USER(username),    
	FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id)
);