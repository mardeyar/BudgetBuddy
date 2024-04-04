package com.budgetbuddy.webdfinal.controller;

import com.budgetbuddy.webdfinal.dao.BudgetDAO;
import com.budgetbuddy.webdfinal.model.Budget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.budgetbuddy.webdfinal.db.SQLConnection.getConnection;

public class BudgetController implements BudgetDAO {
    private static final String INSERT = "INSERT INTO budgets(user_id, category, budget_amount, month) VALUES (?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM budgets WHERE user_id = ?";

    @Override
    public void insert(Budget budget) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(INSERT);
            stmt.setInt(1, budget.getUserId());
            stmt.setString(2, budget.getCategory());
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
    public List<Budget> select(int userId) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        List<Budget> userBudgets = new ArrayList<>();

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(SELECT);
            stmt.setInt(1, userId);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                userBudgets.add(new Budget(
                        resultSet.getInt("budget_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("category"),
                        resultSet.getDouble("budget_amount"),
                        resultSet.getString("month")
                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connection.close();
            if (stmt != null) stmt.close();
            if (resultSet != null) resultSet.close();
        }
        return userBudgets;
    }
}
