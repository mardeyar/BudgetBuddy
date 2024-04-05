# BudgetBuddy
## About
BudgetBuddy is a web application designed to assist you in keeping track of your finances such as budgeting, transactions and investments.

## Technicals
J2EE, JSP, MySQL, JDBC, Java, HTML, CSS

## Pre-Requisites
- [IntelliJ](https://www.jetbrains.com/idea/download/?section=windows)
- [XAMPP Control Panel](https://www.apachefriends.org/download.html)
- [TomCat](https://tomcat.apache.org/download-90.cgi) or [GlassFish](https://glassfish.org/download)

## Getting Started
Clone this repo to your local

```https://github.com/mardeyar/BudgetBuddy.git```

Ensure your instance of MySQL is up and running the run the following sql script:

```sql
CREATE DATABASE IF NOT EXISTS budgetbuddy;

USE budgetbuddy;

CREATE TABLE users (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL
);

CREATE TABLE transactions (
	transaction_id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	transaction_amount DECIMAL(10, 2) NOT NULL,
	tags VARCHAR(255) NOT NULL,
	transaction_date DATE NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE budgets (
	budget_id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	category VARCHAR(100) NOT NULL,
	budget_amount DECIMAL(10, 2) NOT NULL,
	month VARCHAR(12) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE investments (
	investment_id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	investment_name VARCHAR(255) NOT NULL,
	investment_amount DECIMAL(10, 2) NOT NULL,
	investment_date DATE NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

Open the project in IntelliJ and run the configured server. Your browser should now open up to BudgetBuddy homepage.

## Visuals
![1](https://github.com/mardeyar/BudgetBuddy/assets/117761940/38522d5a-f6db-4771-a549-1274aa2840b7)


