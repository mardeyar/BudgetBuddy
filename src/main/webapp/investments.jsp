<%@ page import="com.budgetbuddy.webdfinal.model.User" %>
<%@ page import="com.budgetbuddy.webdfinal.controller.InvestmentController" %>
<%@ page import="java.util.List" %>
<%@ page import="com.budgetbuddy.webdfinal.model.Investment" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Investments</title>
    <link rel="stylesheet" href="css/financeform.css">
    <link rel="icon" type="image/icon" href="images/icon.png">
</head>
<body>
    <%--Set user variable and session--%>
    <%User user = (User) session.getAttribute("user"); %>

    <%--View--%>
    <div class="container">
        <h1>Track your investments</h1>

        <form id="form" action="${pageContext.request.contextPath}/addInvestment" method="post">
            <input type="hidden" id="userId" name="userId" value="<%= user.getUserId()%>">

            <label for="investmentName">Investment Name</label>
            <input type="text" id="investmentName" name="investmentName" placeholder="Ex. Fortune 500" required>

            <label for="investmentAmount">Investment Amount</label>
            <input type="text" id="investmentAmount" name="investmentAmount" placeholder="Ex. $100.00" required>

            <label for="investmentDate">Investment Date</label>
            <input type="date" id="investmentDate" name="investmentDate" required>

            <button id="btn" type="submit">Add Investment</button>
            <a id="home-link" href="home.jsp">Return Home</a>
        </form>
    </div>


    <div class="finance-container">
        <h2>Your Investments</h2>
        <table id="finance-table">
            <%
                InvestmentController investmentList = new InvestmentController();
                List<Investment> investments;

                try {
                    investments = investmentList.select(user.getUserId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                investments.sort(Comparator.comparing(Investment::getInvestmentDate).reversed());

                // If no investments, display message
                if (investments.isEmpty()) {
            %>
            <tr>
                <td>You have not made any investments yet</td>
            </tr>
            <% } else { %>
            <tr>
                <th>Date</th>
                <th>Name</th>
                <th>Amount</th>
            </tr>
            <%
                for (Investment investment : investments) {
                    String dollarFormat = String.format("%.2f", investment.getInvestmentAmount());
            %>
            <tr>
                <td id="investmentDate-display"><%= investment.getInvestmentDate() %></td>
                <td id="investmentName-display"><%= investment.getInvestmentName() %></td>
                <td id="investmentAmount-display">$<%= dollarFormat %></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>

</body>
</html>
