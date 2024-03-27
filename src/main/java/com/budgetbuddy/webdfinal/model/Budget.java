package com.budgetbuddy.webdfinal.model;

public class Budget {
    private int budgetId;
    private int userId;
    private int categoryId;
    private double budgetAmount;
    private String month;

    // Various constructor methods for different budget types
    public Budget(int budgetId, int userId, int categoryId, double budgetAmount, String month) {
        this.budgetId = budgetId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.budgetAmount = budgetAmount;
        this.month = month;
    }

    public Budget(int userId, int categoryId, double budgetAmount, String month) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.budgetAmount = budgetAmount;
        this.month = month;
    }

    public Budget() {}

    // Getters/setter
    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
