<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <%-- Success message displayed after registration --%>
        <%
            String message = request.getParameter("message");
            if (message != null && message.equals("success")) {
        %>
        <div class="success-message">
            User registered successfully! Please login.
        </div>
        <%
            }
        %>

        <img src="AUCA_logo.jpg" alt="AUCA Logo">
        <h2>Login</h2>
        <form action="LoginServlet" method="post" onsubmit="return validateForm()">
            <label for="email">Email:</label><br>
            <input type="email" id="email" name="email" required><br>
            <label for="password">Password:</label><br>
            <input type="password" id="password" name="password" required><br>
            <label for="role">Role:</label><br>
            <select id="role" name="role">
                <option value="ADMIN">Admin</option>
                <option value="STUDENT">Student</option>
                <option value="TEACHER">Teacher</option>
            </select><br>
            <button type="submit">Login</button>
        </form>
        <p>Don't have an account? <a href="signup.jsp">Sign up</a></p>
    </div>

    <script>
        function validateForm() {
            // Add client-side validation logic here
            return true; // Return true if form is valid, false otherwise
        }
    </script>
</body>
</html>
