<%@ page import="com.budgetbuddy.webdfinal.model.User" %>
<%@ page import="com.budgetbuddy.webdfinal.controller.BudgetController" %>
<%@ page import="com.budgetbuddy.webdfinal.model.Budget" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Comparator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Budget</title>
    <link rel="stylesheet" href="css/financeform.css">
    <link rel="icon" type="image/icon" href="images/icon.png">
</head>
<body>
    <%--Set user variable and session--%>
    <%User user = (User) session.getAttribute("user"); %>

    <%--View--%>
    <div class="container">
        <h2>Track your budgets</h2>

        <form id="form" action="${pageContext.request.contextPath}/addBudget" method="post">
            <input type="hidden" id="userId" name="userId" value="<%= user.getUserId()%>">

            <label for="budgetCategory">Category</label>
            <input type="text" id="budgetCategory" name="budgetCategory" placeholder="Ex. Groceries" required>

            <label for="budgetAmount">Amount</label>
            <input type="text" id="budgetAmount" name="budgetAmount" placeholder="Ex. $100.00" required>

            <label for="budgetMonth">Month</label>
            <select id="budgetMonth" name="budgetMonth" required>
                <option value="" disabled selected>Select a month</option>
                <option value="January">January</option>
                <option value="February">February</option>
                <option value="March">March</option>
                <option value="April">April</option>
                <option value="May">May</option>
                <option value="June">June</option>
                <option value="July">July</option>
                <option value="August">August</option>
                <option value="September">September</option>
                <option value="October">October</option>
                <option value="November">November</option>
                <option value="December">December</option>
            </select>

            <button id="btn" type="submit">Add Budget</button>
            <a id="home-link" href="home.jsp">Return Home</a>
        </form>
    </div>

    <div class="finance-container">
        <h2>Your Monthly Budgets</h2>
        <table id="finance-table">
            <%
                BudgetController budgetList = new BudgetController();
                List<Budget> budgets;

                try {
                    budgets = budgetList.select(user.getUserId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                budgets.sort(Comparator.comparing(Budget::getMonth));

                // If no budgets entered, display message
                if (budgets.isEmpty()) {
            %>
            <tr>
                <td>You have not tracked any budgets yet</td>
            </tr>
            <% } else { %>
            <tr>
                <th>Month</th>
                <th>Category</th>
                <th>Amount</th>
            </tr>
            <%
                for (Budget budget : budgets) {
                    String dollarFormat = String.format("%.2f", budget.getBudgetAmount());
            %>
            <tr>
                <td id="budgetMonth-display"><%= budget.getMonth() %></td>
                <td id="categoryName-display"><%= budget.getCategory() %></td>
                <td id="budgetAmount-display">$<%= dollarFormat %></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</body>
</html>
