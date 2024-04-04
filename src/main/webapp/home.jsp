<%@ page import="com.budgetbuddy.webdfinal.model.User" %>
<%@ page import="com.budgetbuddy.webdfinal.controller.TransactionController" %>
<%@ page import="java.util.List" %>
<%@ page import="com.budgetbuddy.webdfinal.model.Transaction" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.budgetbuddy.webdfinal.controller.BudgetController" %>
<%@ page import="com.budgetbuddy.webdfinal.model.Budget" %>
<%@ page import="com.budgetbuddy.webdfinal.controller.InvestmentController" %>
<%@ page import="com.budgetbuddy.webdfinal.model.Investment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Dashboard</title>
    <link rel="stylesheet" href="css/home.css">
    <link rel="icon" type="image/icon" href="images/icon.png">
</head>
<body>
    <%--Start with a conditional: show content if valid session, else, redirect to index.jsp--%>
    <%User user = (User) session.getAttribute("user"); %>
    <% HttpSession activeSession = request.getSession(false); %>
    <% if (activeSession == null || activeSession.getAttribute("user") == null) { %>
    <% response.sendRedirect("index.jsp"); %>
    <% } else { %>
    <div class="home-header">
        <div id="profile-card">
            <img id="profile-icon" src="images/profile.png" alt="profile.png">
            <h2 id="welcome">Welcome back, <%= ((User) session.getAttribute("user")).getFirstName()%>!</h2>
        </div>
        <a id="logout-link" href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>

    <h1>Finances - At A Glance</h1>

    <div id="card-glance">
        <a href="transactions.jsp">
            <div class="budget-card">
                <p class="glance-card-title">Transactions</p>
                <%
                    TransactionController tc = new TransactionController();
                    List<Transaction> transactions = new ArrayList<>();

                    try {
                        transactions = tc.select(user.getUserId());
                    } catch (SQLException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    transactions.sort(Comparator.comparing(Transaction::getTransactionDate).reversed());
                    if (transactions.isEmpty()) {
                %>
                <img class="shrug" src="images/shrug.png">
                <p class="none-available">No recent transactions available</p>
                <% } else {
                    for (int i = 0; i < 5 && i < transactions.size(); i++) {
                        Transaction transaction = transactions.get(i);
                        SimpleDateFormat df = new SimpleDateFormat("MMMM dd/yyyy");
                        String date = df.format(transaction.getTransactionDate());
                        String dollarFormat = String.format("%.2f", transaction.getTransactionAmount());
                %>
                <div class="recent-finance">
                    <p><%= date %> - $<%= dollarFormat %></p>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </a>

        <a href="budget.jsp">
            <div class="budget-card">
                <p class="glance-card-title">Budgets</p>
                <%
                    BudgetController bc = new BudgetController();
                    List<Budget> budgets = new ArrayList<>();

                    try {
                        budgets = bc.select(user.getUserId());
                    } catch (SQLException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    budgets.sort(Comparator.comparing(Budget::getMonth).reversed());
                    if (budgets.isEmpty()) {
                %>
                <img class="shrug" src="images/shrug.png">
                <p class="none-available">No recent budgets available</p>
                <% } else {
                    for (int i = 0; i < 5 && i < budgets.size(); i++) {
                        Budget budget = budgets.get(i);
                        String dollarFormat = String.format("%.2f", budget.getBudgetAmount());
                %>
                <div class="recent-finance">
                    <p><%= budget.getCategory() %> - $<%= dollarFormat %></p>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </a>

        <a href="investments.jsp">
        <div class="budget-card">
            <p class="glance-card-title">Investments</p>
            <%
                InvestmentController ic = new InvestmentController();
                List<Investment> investments = new ArrayList<>();

                try {
                    investments = ic.select(user.getUserId());
                } catch (SQLException e) {
                    System.err.println("Error: " + e.getMessage());
                }
                investments.sort(Comparator.comparing(Investment::getInvestmentDate).reversed());
                if (investments.isEmpty()) {
            %>
            <img class="shrug" src="images/shrug.png">
            <p class="none-available">No recent investments available</p>
            <% } else {
                for (int i = 0; i < 5 && i < investments.size(); i++) {
                    Investment investment = investments.get(i);
                    SimpleDateFormat df = new SimpleDateFormat("MMMM dd/yyyy");
                    String date = df.format(investment.getInvestmentDate());
                    String dollarFormat = String.format("%.2f", investment.getInvestmentAmount());
            %>
            <div class="recent-finance">
                <p><%= date %> - $<%= dollarFormat %></p>
            </div>
            <%
                    }
                }
            %>
        </div>
        </a>
    </div>

    <% } %>
</body>
</html>
