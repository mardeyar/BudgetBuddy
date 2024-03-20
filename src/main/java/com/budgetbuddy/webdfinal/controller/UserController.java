package com.budgetbuddy.webdfinal.controller;

import com.budgetbuddy.webdfinal.dao.UserDAO;
import com.budgetbuddy.webdfinal.db.SQLConnection;
import com.budgetbuddy.webdfinal.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.budgetbuddy.webdfinal.db.SQLConnection.getConnection;

public class UserController implements UserDAO {
    private static final String INSERT = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
    private static final String SESSION_INFO = "SELECT * FROM users WHERE email = ?";
    private static final String CHECK_EXISTING_EMAIL = "SELECT COUNT(*) FROM users WHERE email = ?";

    @Override
    public void insert(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(INSERT);

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception thrown: " + e.getMessage());
        } finally {
            stmt.close();
            connection.close();
        }
    }

    @Override
    public User getSessionInfo(String email) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = SQLConnection.getConnection();
            stmt = connection.prepareStatement(SESSION_INFO);
            stmt.setString(1, email);
            resultSet = stmt.executeQuery();

            // TODO: Add logic for whatever I'm going to use this method for
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return user;
    }

    @Override
    public List<User> select() throws SQLException {
        return null;
    }

    @Override
    public boolean emailExists(String email) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(CHECK_EXISTING_EMAIL);
            stmt.setString(1, email);
            resultSet = stmt.executeQuery();
            resultSet.next();
            int emailCount = resultSet.getInt(1);
            return emailCount > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
}
