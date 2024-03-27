package com.budgetbuddy.webdfinal.model;

import java.sql.Date;

public class Investment {
    private int investmentId;
    private int userId;
    private String investmentName;
    private double investmentAmount;
    private Date investmentDate;

    // Constructors
    public Investment(int investmentId, int userId, String investmentName, double investmentAmount, Date investmentDate) {
        this.investmentId = investmentId;
        this.userId = userId;
        this.investmentName = investmentName;
        this.investmentAmount = investmentAmount;
        this.investmentDate = investmentDate;
    }

    public Investment(int userId, String investmentName, double investmentAmount, Date investmentDate) {
        this.userId = userId;
        this.investmentName = investmentName;
        this.investmentAmount = investmentAmount;
        this.investmentDate = investmentDate;
    }

    public Investment() {}

    // Getters/setters
    public int getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(int investmentId) {
        this.investmentId = investmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }

    public double getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(double investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public Date getInvestmentDate() {
        return investmentDate;
    }

    public void setInvestmentDate(Date investmentDate) {
        this.investmentDate = investmentDate;
    }
}
