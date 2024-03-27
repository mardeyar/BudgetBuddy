package com.budgetbuddy.webdfinal.dao;

import com.budgetbuddy.webdfinal.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {
    void insert(Category category) throws SQLException;
    List<Category> select() throws SQLException;
}
