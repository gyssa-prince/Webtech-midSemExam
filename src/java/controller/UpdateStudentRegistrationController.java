package controller;

import Model.Academic;
import Model.Semester;
import Model.Student;
import Model.StudentRegistration;
import dao.StudentRegistrationDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gyssagara
 */

@WebServlet("/UpdateStudentRegistrationController")
public class UpdateStudentRegistrationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentRegistrationDao studentRegistrationDao;

    @Override
    public void init() throws ServletException {
        studentRegistrationDao = new StudentRegistrationDao();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Long studentId = Long.parseLong(request.getParameter("studentId"));
            Date registrationDate = dateFormat.parse(request.getParameter("registrationDate"));
            Long registrationId = Long.parseLong(request.getParameter("registrationId"));
            Long departmentId = Long.parseLong(request.getParameter("departmentId"));
            Long semesterId = Long.parseLong(request.getParameter("semesterId"));

            StudentRegistration studentRegis = new StudentRegistration();
            studentRegis.setRegistrationId(registrationId);
            Student student = new Student();
            student.setStudentId(studentId);
            studentRegis.setStudent(student);
            studentRegis.setRegistrationDate(registrationDate);
            Academic department = new Academic();
            department.setAcademicUnitId(departmentId);
            studentRegis.setDepartment(department);
            Semester semester = new Semester();
            semester.setSemesterId(semesterId);
            studentRegis.setSemester(semester);

            studentRegistrationDao.updateStudentRegistration(studentRegis);

            sendResponse(response, "Student Registration has been updated successfully!");
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
