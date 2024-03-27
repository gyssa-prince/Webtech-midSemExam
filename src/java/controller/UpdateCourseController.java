package controller;

import Model.Academic;
import Model.Course;
import Model.Semester;
import dao.CourseDao;
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

@WebServlet("/UpdateCourseController")
public class UpdateCourseController extends HttpServlet {
    private CourseDao courseDao;
    
    @Override
    public void init() throws ServletException {
        courseDao = new CourseDao();        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For simplicity, handle GET requests the same way as PUT requests
        doPut(request, response);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Update Course
            Long courseId = Long.parseLong(request.getParameter("courseId"));
            String courseName = request.getParameter("courseName");
            Long semesterId = Long.parseLong(request.getParameter("semesterId"));
            Long departmentId = Long.parseLong(request.getParameter("departmentId"));
            
            Course course = new Course();
            course.setId(courseId);
            course.setCourseName(courseName);
            
            Semester semester = new Semester();
            semester.setSemesterId(semesterId);
            course.setSemester(semester);
            
            Academic department = new Academic();
            department.setAcademicUnitId(departmentId);
            course.setDepartment(department);
            
            courseDao.updateCourse(course);       
            
            sendResponse(response, "Course updated successfully!");
        } catch (NumberFormatException e) {
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
