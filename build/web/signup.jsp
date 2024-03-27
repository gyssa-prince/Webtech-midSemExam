<%-- 
    Document   : signup
    Created on : Mar 16, 2024, 8:57:59 AM
    Author     : N.Maliki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AUCA STUDENT REGISTRATION</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background: url("AUCA BG.jpg") no-repeat center center;
            }

            .container {
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                padding: 20px;
                width: 300px;
            }

            .container h2 {
                text-align: center;
                margin-bottom: 20px;
            }

            .container img {
                display: block;
                margin: 0 auto;
                width: 100px; /* Adjust width as needed */
                margin-bottom: 20px;
            }

            .container form input,
            .container form button,
            .container form select {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            .container form button {
                border: none;
                border-radius: 5px;
                background-color: #007bff;
                color: #fff;
                cursor: pointer;
            }

            .container form button:hover {
                background-color: #0056b3;
            }

            .container p {
                text-align: center;
                margin-top: 15px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <img src="AUCA logo.jpg" alt="AUCA Logo">
            <center><h1> Sign Up</h1></center>
            <form action="SignUpServlet" method="post">
                <label>Email:</label><br>
                <input type="email" name="email"><br>
                <label>Password:</label><br>
                <input type="password" name="password"><br>
                <label>Role:</label><br>
                <select name="role">
                    <option value="ADMIN">Admin</option>
                    <option value="STUDENT">Student</option>
                    <option value="TEACHER">Teacher</option>
                </select><br>
                <input type="submit" value="Sign Up">
            </form>
            <p>Have an account? <a href="login.jsp">Login</a></p>
        </div>
    </body>
</html>
