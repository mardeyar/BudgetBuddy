package com.budgetbuddy.webdfinal.controller;

import com.budgetbuddy.webdfinal.dao.CategoryDAO;
import com.budgetbuddy.webdfinal.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.budgetbuddy.webdfinal.db.SQLConnection.getConnection;

public class CategoryController implements CategoryDAO {
    public static final String INSERT = "INSERT INTO categories(category_name) VALUES (?)";
    public static final String SELECT = "SELECT * FROM categories";

    @Override
    public void insert(Category category) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(INSERT);
            stmt.setString(1, category.getCategoryName());

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            connection.close();
            stmt.close();
        }
    }

    @Override
    public List<Category> select() throws SQLException {
        return null;
    }
}
