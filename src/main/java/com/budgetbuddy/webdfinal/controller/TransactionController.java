package com.budgetbuddy.webdfinal.controller;

import com.budgetbuddy.webdfinal.dao.TransactionDAO;
import com.budgetbuddy.webdfinal.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.budgetbuddy.webdfinal.db.SQLConnection.getConnection;

public class TransactionController implements TransactionDAO {
    private static final String INSERT = "INSERT INTO transactions(user_id, transaction_amount, transaction_type, category_id, tags, transaction_date) ";
    private static final String SELECT = "SELECT * FROM transactions";
    @Override
    public void insert(Transaction transaction) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(INSERT);

            stmt.setInt(1, transaction.getUserId());
            stmt.setDouble(2, transaction.getTransactionAmount());
            stmt.setString(3, transaction.getTransactionType());
            stmt.setInt(4, transaction.getCategoryId());
            stmt.setString(5, transaction.getTags());
            stmt.setDate(6, transaction.getTransactionDate());

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            connection.close();
            stmt.close();
        }
    }

    @Override
    public List<Transaction> select() throws SQLException {
        return null;
    }
}
