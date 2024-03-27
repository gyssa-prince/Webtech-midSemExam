package controller;

import dao.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.Role;
import Model.User;

/**
 * @author Gyssagara
 */


@WebServlet("/SigninServlet")
public class SigninServlet extends HttpServlet {

    private static final int SESSION_TIMEOUT = 60;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String selectedRole = request.getParameter("role");

        UserDao userDao = new UserDao();
        User existingUser = userDao.findByEmail(new User(null, email, null, null));

        if (existingUser != null && existingUser.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(SESSION_TIMEOUT); // Set session timeout to a constant value

            Role userRole = existingUser.getStatus();

            // Check if the selected role matches the user's role in the database
            if (selectedRole.equals(userRole.toString())) {
                // Redirect users based on their roles
                switch (userRole) {
                    case ADMIN:
                        response.sendRedirect("indexADMIN.html");
                        break;
                    case STUDENT:
                        response.sendRedirect("indexADMIN.html");
                        break;
                    case TEACHER:
                        response.sendRedirect("indexADMIN.html");
                        break;
                    default:
                        // Handle unexpected role
                        response.sendRedirect("login.jsp?error=invalid_role");
                        break;
                }
            } else {
                // Redirect with an error message if the selected role doesn't match
                response.sendRedirect("login.jsp?error=invalid_role");
            }
        } else {
            // Redirect if login credentials are invalid
            response.sendRedirect("login.jsp");
        }
    }
}
