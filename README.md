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
![1](https://github.com/mardeyar/BudgetBuddy/assets/117761940/f1f0ea69-bb9e-4004-a102-e1ea1cee95e3)

![2](https://github.com/mardeyar/BudgetBuddy/assets/117761940/606fdbcf-6bc5-43ae-bdc2-573c2de2faef)

![3](https://github.com/mardeyar/BudgetBuddy/assets/117761940/e163fb98-951a-4459-a5dd-d4497805b7e0)

![4](https://github.com/mardeyar/BudgetBuddy/assets/117761940/912eedbc-ef87-4c66-be61-09a03fb90690)

![5](https://github.com/mardeyar/BudgetBuddy/assets/117761940/d0f1954e-1c20-4cf4-8266-6d406e5a4960)

![6](https://github.com/mardeyar/BudgetBuddy/assets/117761940/0b114e36-5df8-4425-8668-b08b10366f80)

![7](https://github.com/mardeyar/BudgetBuddy/assets/117761940/14046d28-e9fe-4251-82c5-f2873c526d20)







