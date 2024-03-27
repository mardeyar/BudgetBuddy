package com.budgetbuddy.webdfinal;

import com.budgetbuddy.webdfinal.controller.UserController;
import com.budgetbuddy.webdfinal.dao.UserDAO;
import com.budgetbuddy.webdfinal.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RegisterServlet extends HttpServlet {
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
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();
        if (url.equals("/register")) {
            register(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        try {
            // Error handling: check for duplicate emails and password mismatch
            if (user.emailExists(email)) {
                request.setAttribute("duplicateEmail", true);
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
                return;
            }

            if (!password.equals(confirmPassword)) {
                request.setAttribute("passwordMismatch", true);
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
                return;
            }

            // Password hashing for secure DB storage
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Create the new registered user object and 'insert' into the DB
            User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setPassword(hashedPassword);

            user.insert(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("login.jsp");
    }
}
