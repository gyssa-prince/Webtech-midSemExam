package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.StudentDao;
import java.io.IOException;
import java.io.PrintWriter;
import Model.Student;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gyssagara
 */

@WebServlet("/UpdateStudentController")
public class UpdateStudentController extends HttpServlet {

    private StudentDao studentDao;

    @Override
    public void init() throws ServletException {
        studentDao = new StudentDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to doPut method for simplicity
        doPut(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Update Student
            Long studentId = Long.parseLong(request.getParameter("studentId"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dateOfBirthString = request.getParameter("dateOfBirth");

            // Parse date of birth string to Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfBirth = dateFormat.parse(dateOfBirthString);

            Student student = new Student(studentId, firstName, lastName, dateOfBirth);
            studentDao.updateStudent(student);

            sendResponse(response, "Student updated successfully!");
        } catch (NumberFormatException | ParseException e) {
            sendErrorResponse(response, "Invalid input format!");
        }
    }

    private void sendResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println(message);
    }

    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        sendResponse(response, errorMessage);
    }
}
