CREATE DATABASE IF NOT EXISTS budgetapp;
USE budgetapp;

INSERT INTO BUDGET (username, categoryId, dateTime, amount, description)
VALUES 
	('rapid84', '1', NOW(), '4.55', 'Housing Mortgage loans')