<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/form.css">
    <title>Sign Up</title>
    <link rel="icon" type="image/icon" href="images/icon.png">
</head>
<body>
    <%--SIGN UP FORM--%>
    <form id="user-form" action="${pageContext.request.contextPath}/register" method="post">
        <div id="form-msg">
            <h1>Sign Up</h1>
            <img id="icon" src="images/register.png" alt="register.png">
        </div>
        <%--Display error messages if email exists or passwords do not match--%>
        <% if (request.getAttribute("duplicateEmail") != null && (boolean) request.getAttribute("duplicateEmail")) { %>
            <p id="error-msg">Email already exists</p>
        <% } %>

        <% if (request.getAttribute("passwordMismatch") != null && (boolean) request.getAttribute("passwordMismatch")) { %>
            <p id="error-msg">Passwords do not match</p>
        <% } %>

        <label for="firstName">First Name</label>
        <input type="text" id="firstName" name="firstName" placeholder="John" required>

        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" name="lastName" placeholder="Doe" required>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" placeholder="example@email.com" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="********" required>

        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="********" required>

        <button id="form-submit-btn" type="submit">Sign Up</button>
        <p id="form-redirect-link">Already have an account? Sign in <a href="login.jsp">here</a></p>
    </form>
</body>
</html>
