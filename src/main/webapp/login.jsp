<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/form.css">
    <title>Sign In</title>
</head>
<body>
    <%--SIGN IN FORM--%>
    <form id="user-form" action="${pageContext.request.contextPath}/login" method="post">
        <div id="form-msg">
            <h1>Welcome back!</h1>
        </div>
        
        <%--Display error messages if login credentials are invalid--%>
        <% if (request.getAttribute("loginFailed") != null && (boolean) request.getAttribute("loginFailed")) { %>
            <p>Invalid email or password: try again</p>
        <% } %>

        <label for="email"></label>
        <input type="email" id="email" name="email" placeholder="Email" required>

        <label for="password"></label>
        <input type="password" id="password" name="password" placeholder="Password" required>

        <button id="form-submit-btn" type="submit">Sign In</button>
        <p id="form-redirect-link">Don't yet have an account? Sign up <a href="register.jsp">here</a></p>
    </form>
</body>
</html>
