<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>BudgetBuddy - Home</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
    <%--Start with a conditional: show content if valid session, else, redirect to index.jsp--%>
<%--    <% HttpSession activeSession = request.getSession(false); %>--%>
<%--    <% if (activeSession == null || activeSession.getAttribute("user") == null) { %>--%>
<%--    <% response.sendRedirect("index.jsp"); %>--%>
<%--    <% } else { %>--%>
        <p>You've cracked the mainframe!</p>
<%--    <% } %>--%>
</body>
</html>
