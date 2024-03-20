package com.budgetbuddy.webdfinal.dao;

import com.budgetbuddy.webdfinal.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void insert(User user) throws SQLException;
    User getSessionInfo(String email) throws SQLException;
    List<User> select() throws SQLException;
    boolean emailExists(String email) throws SQLException;
}
