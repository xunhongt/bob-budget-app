CREATE DATABASE IF NOT EXISTS budgetapp;
USE budgetapp;

INSERT INTO CATEGORY (category_id, category_name, category_type)
VALUES 
	('1', 'HOUSING', 'E'),
	('2', 'TRANSPORT', 'E'),
	('3', 'FOOD', 'E'),
	('4', 'UTILITIES', 'E'),
	('5', 'CLOTHING', 'E'),
	('6', 'MEDICAL', 'E'),
	('7', 'INSURANCE', 'E'),
	('8', 'HOUSEHOLD ITEMS', 'E'),
	('9', 'PERSONAL', 'E'),
	('10', 'DEBT', 'E'),
	('11', 'EDUCATION', 'E'),
	('12', 'ENTERTAINMENT', 'E'),
	('13', 'GIFTS', 'E'),
	('14', 'SALARY', 'I'),    
	('15', 'SAVINGS', 'I');