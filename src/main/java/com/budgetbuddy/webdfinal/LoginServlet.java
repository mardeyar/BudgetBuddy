package com.budgetbuddy.webdfinal;

import com.budgetbuddy.webdfinal.controller.UserController;
import com.budgetbuddy.webdfinal.dao.UserDAO;
import com.budgetbuddy.webdfinal.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private UserDAO user;

    @Override
    public void init() throws ServletException {
        super.init();
        user = new UserController();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserController();

        try {
            List<User> users = userDAO.select();
            request.setAttribute("users", users);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();
        if (url.equals("/login")) {
            login(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User activeUser = user.getSessionInfo(email);

            if (activeUser != null && BCrypt.checkpw(password, activeUser.getPassword())) {
                // Passwords and email match: start session. Move to home.jsp and stop further code execution
                HttpSession session = request.getSession();
                session.setAttribute("user", activeUser);
                response.sendRedirect("home.jsp");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If login failed, do not move past the sign-in page
        request.setAttribute("loginFailed", true);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
