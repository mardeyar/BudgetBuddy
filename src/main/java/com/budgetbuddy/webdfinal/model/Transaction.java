package com.budgetbuddy.webdfinal.model;

import java.sql.Date;

public class Transaction {
    private int transactionId;
    private int userId;
    private double transactionAmount;
    private String transactionType;
    private int categoryId;
    private String tags;
    private Date transactionDate;

    // Constructors
    public Transaction(int transactionId, int userId, double transactionAmount, String transactionType, int categoryId, String tags, Date transactionDate) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.categoryId = categoryId;
        this.tags = tags;
        this.transactionDate = transactionDate;
    }

    public Transaction(int userId, double transactionAmount, String transactionType, int categoryId, String tags, Date transactionDate) {
        this.userId = userId;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.categoryId = categoryId;
        this.tags = tags;
        this.transactionDate = transactionDate;
    }

    public Transaction() {}

    // Getters/setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
