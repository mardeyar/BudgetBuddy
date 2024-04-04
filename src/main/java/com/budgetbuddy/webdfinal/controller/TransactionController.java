package com.budgetbuddy.webdfinal.controller;

import com.budgetbuddy.webdfinal.dao.TransactionDAO;
import com.budgetbuddy.webdfinal.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.budgetbuddy.webdfinal.db.SQLConnection.getConnection;

public class TransactionController implements TransactionDAO {
    private static final String INSERT = "INSERT INTO transactions(user_id, transaction_amount, tags, transaction_date) VALUES (?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM transactions WHERE user_id = ?";
    @Override
    public void insert(Transaction transaction) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(INSERT);

            stmt.setInt(1, transaction.getUserId());
            stmt.setDouble(2, transaction.getTransactionAmount());
            stmt.setString(3, transaction.getTags());
            stmt.setDate(4, transaction.getTransactionDate());

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            connection.close();
            stmt.close();
        }
    }

    @Override
    public List<Transaction> select(int userId) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        List<Transaction> userTransactions = new ArrayList<>();

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(SELECT);
            stmt.setInt(1, userId);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                userTransactions.add(new Transaction(
                        resultSet.getInt("transaction_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getDouble("transaction_amount"),
                        resultSet.getString("tags"),
                        resultSet.getDate("transaction_date")
                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connection.close();
            if (stmt != null) stmt.close();
            if (resultSet != null) resultSet.close();
        }
        return userTransactions;
    }
}
