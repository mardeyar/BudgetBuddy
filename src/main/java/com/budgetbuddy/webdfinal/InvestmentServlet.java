package com.budgetbuddy.webdfinal;

import com.budgetbuddy.webdfinal.controller.InvestmentController;
import com.budgetbuddy.webdfinal.dao.InvestmentDAO;
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
import java.util.List;

public class InvestmentServlet extends HttpServlet {
    private InvestmentDAO investment;
    @Override
    public void init() throws ServletException {
        super.init();
        investment = new InvestmentController();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get activeUser from this session
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            int userId = ((User) session.getAttribute("user")).getUserId();

            try {
                List<Investment> investments = investment.select(userId);
                request.setAttribute("investments", investments);
                request.getRequestDispatcher("investments.jsp").forward(request, response);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            // If session or user attribute is null, redirect to the login page
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/addInvestment":
                addInvestment(request, response);
                break;
            default:
                response.sendRedirect("home.jsp");
                break;
        }
    }

    public void addInvestment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                int userId = user.getUserId();
                String investmentName = request.getParameter("investmentName");
                Double investmentAmount = Double.valueOf(request.getParameter("investmentAmount"));
                Date investmentDate = Date.valueOf(request.getParameter("investmentDate"));

                try {
                    Investment newInvestment = new Investment();
                    newInvestment.setUserId(userId);
                    newInvestment.setInvestmentName(investmentName);
                    newInvestment.setInvestmentAmount(investmentAmount);
                    newInvestment.setInvestmentDate(investmentDate);

                    investment.insert(newInvestment);
                } catch (SQLException e) {
                    System.err.println("Error: " + e.getMessage());
                    response.sendRedirect("error.jsp");
                    return;
                }
                response.sendRedirect("investments.jsp");
                return;
            }
        }
        // If session is null, go to log in
        response.sendRedirect("login.jsp");
    }
}
