package com.budgetbuddy.webdfinal.dao;

import com.budgetbuddy.webdfinal.model.Investment;

import java.sql.SQLException;
import java.util.List;

public interface InvestmentDAO {
    void insert(Investment investment) throws SQLException;
    List<Investment> select() throws SQLException;
}
