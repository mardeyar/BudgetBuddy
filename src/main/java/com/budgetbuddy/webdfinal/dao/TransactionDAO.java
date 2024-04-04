package com.budgetbuddy.webdfinal.dao;

import com.budgetbuddy.webdfinal.model.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDAO {
    void insert(Transaction transaction) throws SQLException;
    List<Transaction> select(int userId) throws SQLException;
}
