<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/form.css">
    <title>Sign In</title>
    <link rel="icon" type="image/icon" href="images/icon.png">
</head>
<body>

    <%--SIGN IN FORM--%>
    <form id="user-form" action="${pageContext.request.contextPath}/login" method="post">
        <div id="form-msg">
            <h1>Sign In</h1>
            <img id="icon" src="images/lock.png" alt="lock.png">
        </div>
        
        <%--Display error messages if login credentials are invalid--%>
        <% if (request.getAttribute("loginFailed") != null && (boolean) request.getAttribute("loginFailed")) { %>
            <p id="error-msg">Invalid email or password: try again</p>
        <% } %>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" placeholder="example@email.com" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="********" required>

        <button id="form-submit-btn" type="submit">Sign In</button>
        <p id="form-redirect-link">Don't yet have an account? Sign up <a href="register.jsp">here</a></p>
    </form>
</body>
</html>
