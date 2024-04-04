package com.budgetbuddy.webdfinal.dao;

import com.budgetbuddy.webdfinal.model.Budget;

import java.sql.SQLException;
import java.util.List;

public interface BudgetDAO {
    void insert(Budget budget) throws SQLException;
    List<Budget> select(int userId) throws SQLException;
}
