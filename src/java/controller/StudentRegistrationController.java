package controller;

import Model.Academic;
import Model.Semester;
import Model.Student;
import Model.StudentRegistration;
import dao.StudentRegistrationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gyssagara
 */

@WebServlet("/StudentRegistrationController")
public class StudentRegistrationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentRegistrationDao studentRegistrationDao;

    @Override
    public void init() throws ServletException {
        studentRegistrationDao = new StudentRegistrationDao();
    }

    /**
     * Handles HTTP GET requests. Forwards to doPost method.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles HTTP POST requests. Inserts student registration.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Parse request parameters
            Long studentId = Long.parseLong(request.getParameter("studentId"));
            Date registrationDate = parseDate(request.getParameter("registrationDate"));
            Long departmentId = Long.parseLong(request.getParameter("departmentId"));
            Long semesterId = Long.parseLong(request.getParameter("semesterId"));

            // Create StudentRegistration object
            StudentRegistration studentRegistration = new StudentRegistration();
            studentRegistration.setStudent(new Student(studentId));
            studentRegistration.setRegistrationDate(registrationDate);
            studentRegistration.setDepartment(new Academic(departmentId));
            studentRegistration.setSemester(new Semester(semesterId));

            // Insert student registration into database
            studentRegistrationDao.createStudentRegistration(studentRegistration);

            // Send success response to client
            sendResponse(response, "Student registration inserted successfully!");
        } catch (ParseException | NumberFormatException e) {
            // Send error response to client
            sendErrorResponse(response, "Invalid input format!");
        }
    }

    /**
     * Handles HTTP DELETE requests. Deletes student registration.
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Parse request parameter
            Long registrationId = Long.parseLong(request.getParameter("registrationId"));

            // Create StudentRegistration object
            StudentRegistration studentRegistration = new StudentRegistration();
            studentRegistration.setRegistrationId(registrationId);

            // Delete student registration from database
            studentRegistrationDao.DeleteStudentRegistration(studentRegistration);

            // Send success response to client
            sendResponse(response, "Student registration deleted successfully!");
        } catch (NumberFormatException e) {
            // Send error response to client
            sendErrorResponse(response, "Invalid registration ID!");
        }
    }

    /**
     * Parses date string in "yyyy-MM-dd" format to Date object.
     */
    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }

    /**
     * Sends success response to client.
     */
    private void sendResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println(message);
    }

    /**
     * Sends error response to client.
     */
    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        sendResponse(response, errorMessage);
    }
}
