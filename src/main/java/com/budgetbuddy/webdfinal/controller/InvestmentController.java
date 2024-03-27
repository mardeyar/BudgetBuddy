package com.budgetbuddy.webdfinal.controller;

import com.budgetbuddy.webdfinal.dao.InvestmentDAO;
import com.budgetbuddy.webdfinal.model.Investment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.budgetbuddy.webdfinal.db.SQLConnection.getConnection;

public class InvestmentController implements InvestmentDAO {
    private static final String INSERT = "INSERT INTO investments(user_id, investment_name, investment_amount, investment_date) VALUES (?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM investments";

    @Override
    public void insert(Investment investment) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(INSERT);

            stmt.setInt(1, investment.getUserId());
            stmt.setString(2, investment.getInvestmentName());
            stmt.setDouble(3, investment.getInvestmentAmount());
            stmt.setDate(4, investment.getInvestmentDate());

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("SQL error: " + e.getMessage());
        } finally {
            connection.close();
            stmt.close();
        }
    }

    @Override
    public List<Investment> select() throws SQLException {
        return null;
    }
}
