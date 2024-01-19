<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
    <h2>Questa è la pagina di Login</h2>
    <% if (request.getAttribute("loginError") != null) { %>
        <p style="color: red;">Errore durante la procedura di login.CREDENZIALI ERRATE</p>
    <% } %>
    <form action="LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        
        <input type="submit" value="Login">
    </form>
</body>
</html>