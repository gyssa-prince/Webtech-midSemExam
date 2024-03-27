package controller;

import Model.Course;
import Model.EQualification;
import Model.Teacher;
import dao.TeacherDao;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gyssagara
 */

@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TeacherDao teacherDao;

    @Override
    public void init() {
        teacherDao = new TeacherDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For simplicity, handle GET requests the same way as DELETE requests
        doDelete(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Delete Teacher
        try {
            Long teacherId = Long.parseLong(request.getParameter("teacherId"));
            Teacher teacher = new Teacher();
            teacher.setId(teacherId);
            teacherDao.DeleteTeacher(teacher);
            sendResponse(response, "Teacher deleted successfully!");
        } catch (NumberFormatException e) {
            sendErrorResponse(response, "Invalid teacher ID!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Insert Teacher
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String qualification = request.getParameter("qualification");
        try {
            Long courseId = Long.parseLong(request.getParameter("courseId"));
            Teacher teacher = new Teacher();
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            teacher.setQualification(EQualification.valueOf(qualification));
            Course course = new Course();
            course.setId(courseId);
            // Assuming you have a method to associate a teacher with a course in your DAO
            teacherDao.createTeacher(teacher);
            sendResponse(response, "Teacher inserted successfully!");
        } catch (NumberFormatException | IllegalArgumentException e) {
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
