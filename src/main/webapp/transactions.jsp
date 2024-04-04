<%@ page import="com.budgetbuddy.webdfinal.model.User" %>
<%@ page import="com.budgetbuddy.webdfinal.controller.TransactionController" %>
<%@ page import="com.budgetbuddy.webdfinal.model.Transaction" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Comparator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Transactions</title>
    <link rel="stylesheet" href="css/financeform.css">
    <link rel="icon" type="image/icon" href="images/icon.png">
</head>
<body>
    <%--Set user variable and session--%>
    <%User user = (User) session.getAttribute("user"); %>

    <%--View--%>
    <div class="container">
        <h2>Track your transactions</h2>

        <form id="form" action="${pageContext.request.contextPath}/addTransaction" method="post">
            <input type="hidden" id="userId" name="userId" value="<%= user.getUserId()%>">

            <label for="transactionAmount">Transaction Amount</label>
            <input type="text" id="transactionAmount" name="transactionAmount" placeholder="Ex. $100.00" required>

            <label for="transactionTags">Tag</label>
            <input type="text" id="transactionTags" name="transactionTags" placeholder="Ex. Groceries" required>

            <label for="transactionDate">Transaction Date</label>
            <input type="date" id="transactionDate" name="transactionDate" required>

            <button id="btn" type="submit">Add Transaction</button>
            <a id="home-link" href="home.jsp">Return Home</a>
        </form>
    </div>

    <div class="finance-container">
        <h2>Your Transactions</h2>
        <table id="finance-table">
            <%
                TransactionController transactionList = new TransactionController();
                List<Transaction> transactions;

                try {
                    transactions = transactionList.select(user.getUserId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                transactions.sort(Comparator.comparing(Transaction::getTransactionDate).reversed());

                // If no budgets entered, display message
                if (transactions.isEmpty()) {
            %>
            <tr>
                <td>You have not tracked any transactions yet</td>
            </tr>
            <% } else { %>
            <tr>
                <th>Date</th>
                <th>Tags</th>
                <th>Amount</th>
            </tr>
            <%
                for (Transaction transaction : transactions) {
                    String dollarFormat = String.format("%.2f", transaction.getTransactionAmount());
            %>

            <tr>
                <td id="transactionDate-display"><%= transaction.getTransactionDate() %></td>
                <td id="transactionTags-display"><%= transaction.getTags() %></td>
                <td id="transactionAmount-display">$<%= dollarFormat %></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</body>
</html>
