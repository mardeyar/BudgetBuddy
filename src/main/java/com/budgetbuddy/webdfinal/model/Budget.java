package com.budgetbuddy.webdfinal.model;

public class Budget {
    private int budgetId;
    private int userId;
    private String category;
    private double budgetAmount;
    private String month;

    // Various constructor methods for different budget types
    public Budget(int budgetId, int userId, String category, double budgetAmount, String month) {
        this.budgetId = budgetId;
        this.userId = userId;
        this.category = category;
        this.budgetAmount = budgetAmount;
        this.month = month;
    }

    public Budget(int userId, String category, double budgetAmount, String month) {
        this.userId = userId;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
