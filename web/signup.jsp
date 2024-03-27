<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AUCA STUDENT REGISTRATION</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <img src="AUCA_logo.jpg" alt="AUCA Logo">
        <h2>Sign Up</h2>
        <form action="SignUpServlet" method="post" onsubmit="return validateForm()">
            <label for="email">Email:</label><br>
            <input type="email" id="email" name="email" required><br>
            <label for="password">Password:</label><br>
            <input type="password" id="password" name="password" required><br>
            <label for="confirmPassword">Confirm Password:</label><br>
            <input type="password" id="confirmPassword" name="confirmPassword" required><br>
            <label for="role">Role:</label><br>
            <select id="role" name="role">
                <option value="ADMIN">Admin</option>
                <option value="STUDENT">Student</option>
                <option value="TEACHER">Teacher</option>
            </select><br>
            <button type="submit">Sign Up</button>
        </form>
        <p>Have an account? <a href="login.jsp">Login</a></p>
    </div>

    <script>
        function validateForm() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            if (password !== confirmPassword) {
                alert("Passwords do not match");
                return false;
            }

            // Add more validation logic here if needed
            return true;
        }
    </script>
</body>
</html>
