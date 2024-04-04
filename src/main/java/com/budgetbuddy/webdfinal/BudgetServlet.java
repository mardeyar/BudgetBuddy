package com.budgetbuddy.webdfinal;

import com.budgetbuddy.webdfinal.controller.BudgetController;
import com.budgetbuddy.webdfinal.dao.BudgetDAO;
import com.budgetbuddy.webdfinal.model.Budget;
import com.budgetbuddy.webdfinal.model.Investment;
import com.budgetbuddy.webdfinal.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BudgetServlet extends HttpServlet {
    private BudgetDAO budget;

    @Override
    public void init() throws ServletException {
        super.init();
        budget = new BudgetController();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            int userId = ((User) session.getAttribute("user")).getUserId();

            try {
                List<Budget> budgets = budget.select(userId);
                request.setAttribute("budgets", budgets);
                request.getRequestDispatcher("budget.jsp").forward(request, response);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/addBudget":
                addBudget(request, response);
                break;
            default:
                response.sendRedirect("home.jsp");
                break;
        }
    }

    public void addBudget(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                int userId = user.getUserId();
                String budgetCategory = request.getParameter("budgetCategory");
                Double budgetAmount = Double.valueOf(request.getParameter("budgetAmount"));
                String budgetMonth = request.getParameter("budgetMonth");

                try {
                    Budget newBudget = new Budget();

                    newBudget.setUserId(userId);
                    newBudget.setCategory(budgetCategory);
                    newBudget.setBudgetAmount(budgetAmount);
                    newBudget.setMonth(budgetMonth);

                    budget.insert(newBudget);
                    System.out.println("Budget inserted successfully.");
                } catch (SQLException e) {
                    System.err.println("Error: " + e.getMessage());
                    response.sendRedirect("error.jsp");
                    return;
                }
                response.sendRedirect("budget.jsp");
                return;
            }
        }
        // If session is null, go to log in
        response.sendRedirect("login.jsp");
    }
}
