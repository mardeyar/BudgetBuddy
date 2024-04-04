package com.budgetbuddy.webdfinal;

import com.budgetbuddy.webdfinal.controller.TransactionController;
import com.budgetbuddy.webdfinal.dao.TransactionDAO;
import com.budgetbuddy.webdfinal.model.Transaction;
import com.budgetbuddy.webdfinal.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TransactionServlet extends HttpServlet {
    private TransactionDAO transaction;

    @Override
    public void init() throws ServletException {
        super.init();
        transaction = new TransactionController();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get activeUser from this session
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            int userId = ((User) session.getAttribute("user")).getUserId();

            try {
                List<Transaction> transactions = transaction.select(userId);
                request.setAttribute("transactions", transactions);
                request.getRequestDispatcher("transactions.jsp").forward(request, response);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            // If session or user attribute is null, redirect to the login page
            response.sendRedirect("login.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/addTransaction":
                addTransaction(request, response);
                break;
            default:
                response.sendRedirect("home.jsp");
                break;
        }
    }

    public void addTransaction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                int userId = user.getUserId();
                Double transactionAmount = Double.valueOf(request.getParameter("transactionAmount"));
                String transactionTags = request.getParameter("transactionTags");
                Date transactionDate = Date.valueOf(request.getParameter("transactionDate"));

                try {
                    Transaction newTransaction = new Transaction();
                    newTransaction.setUserId(userId);
                    newTransaction.setTransactionAmount(transactionAmount);
                    newTransaction.setTags(transactionTags);
                    newTransaction.setTransactionDate(transactionDate);

                    transaction.insert(newTransaction);
                } catch (SQLException e) {
                    System.err.println("Error: " + e.getMessage());
                    response.sendRedirect("error.jsp");
                    return;
                }
                response.sendRedirect("transactions.jsp");
                return;
            }
        }
        // If session is null, redirect to log in
        response.sendRedirect("login.jsp");
    }
}
