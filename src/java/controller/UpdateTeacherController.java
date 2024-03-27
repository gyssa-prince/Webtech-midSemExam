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

@WebServlet("/UpdateTeacherController")
public class UpdateTeacherController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TeacherDao teacherDao;

    @Override
    public void init() {
        teacherDao = new TeacherDao();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long teacherId = Long.parseLong(request.getParameter("teacherId"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String qualification = request.getParameter("qualification");
            Long courseId = Long.parseLong(request.getParameter("courseId"));

            Teacher teacher = new Teacher();
            teacher.setId(teacherId);
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            teacher.setQualification(EQualification.valueOf(qualification));
            Course course = new Course();
            course.setId(courseId);
            teacher.setCourse(course);

            teacherDao.updateTeacher(teacher);

            sendResponse(response, "Teacher updated successfully!");
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
