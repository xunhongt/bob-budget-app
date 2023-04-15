CREATE DATABASE IF NOT EXISTS budgetapp;
USE budgetapp;

CREATE TABLE CATEGORY(
    category_id INT,
    category_name VARCHAR(100),
    category_type CHAR(1),
    PRIMARY KEY (category_id)
);