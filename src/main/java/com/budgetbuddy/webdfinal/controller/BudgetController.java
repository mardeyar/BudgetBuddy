package com.budgetbuddy.webdfinal.controller;

import com.budgetbuddy.webdfinal.dao.BudgetDAO;
import com.budgetbuddy.webdfinal.model.Budget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.budgetbuddy.webdfinal.db.SQLConnection.getConnection;

public class BudgetController implements BudgetDAO {
    private static final String INSERT = "INSERT INTO budgets(user_id, category_id, budget_amount, month) VALUES (?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM budgets";

    @Override
    public void insert(Budget budget) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(INSERT);
            stmt.setInt(1, budget.getUserId());
            stmt.setInt(2, budget.getCategoryId());
            stmt.setDouble(3, budget.getBudgetAmount());
            stmt.setString(4, budget.getMonth());

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            connection.close();
            stmt.close();
        }
    }

    @Override
    public List<Budget> select() throws SQLException {
        return null;
    }
}
