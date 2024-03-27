package controller;

import dao.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Role;
import Model.User;

/**
 *
 * @author Gyssagara
 */

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // Role from the registration form

        // Create a new user with the provided details
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setStatus(Role.valueOf(role)); // Convert role string to enum

        // Access the UserDao to create the user
        UserDao userDao = new UserDao();
        userDao.createUser(newUser);

        // Redirect to the login page after successful registration
        response.sendRedirect("login.jsp");
    }
}
