package com.budgetbuddy.webdfinal.controller;

import com.budgetbuddy.webdfinal.dao.InvestmentDAO;
import com.budgetbuddy.webdfinal.model.Investment;
import com.budgetbuddy.webdfinal.model.User;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.budgetbuddy.webdfinal.db.SQLConnection.getConnection;

public class InvestmentController implements InvestmentDAO {
    private static final String INSERT = "INSERT INTO investments(user_id, investment_name, investment_amount, investment_date) VALUES (?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM investments WHERE user_id = ?";

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
    public List<Investment> select(int userId) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        List<Investment> userInvestments = new ArrayList<>();

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(SELECT);
            stmt.setInt(1, userId);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                userInvestments.add(new Investment(
                        resultSet.getInt("investment_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("investment_name"),
                        resultSet.getDouble("investment_amount"),
                        resultSet.getDate("investment_date")
                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connection.close();
            if (stmt != null) stmt.close();
            if (resultSet != null) resultSet.close();
        }
        return userInvestments;
    }
}
