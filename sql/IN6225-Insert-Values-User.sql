CREATE DATABASE IF NOT EXISTS budgetapp;
USE budgetapp;

INSERT INTO USER (username, first_name, last_name, password, email, role)
VALUES 
	('testadmin', 'Test Admin', '1', '$2a$12$x0q6JpX4vhYsXHQw12RSNu.sG8RGwNEewtvdCekkldkF8eV0vLg8a', 'testadmin1@gmail.com', 'ADMIN'),
	('testuser', 'Test User', '1', '$2a$12$x0q6JpX4vhYsXHQw12RSNu.sG8RGwNEewtvdCekkldkF8eV0vLg8a', 'testuser1@gmail.com', 'USER')