package controller;

import Model.Academic;
import Model.Course;
import Model.Semester;
import dao.CourseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CourseController
 * @author Gyssagara
 */
@WebServlet("/CourseController")
public class CourseController extends HttpServlet {

    private CourseDao courseDao;

    public void init() {
        courseDao = new CourseDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Here you can add logic to retrieve data if needed
        // For example:
        // List<Course> courses = courseDao.getAllCourses();
        // Then you can forward the request to a JSP to display the data
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Delete Course
            Long courseId = Long.parseLong(request.getParameter("courseId"));
            Course course = new Course();
            course.setId(courseId);
            courseDao.deleteCourse(course);
            PrintWriter out = response.getWriter();
            out.println("Course deleted successfully!");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.println("Invalid course ID!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Insert Course
            String courseName = request.getParameter("courseName");
            Long semesterId = Long.parseLong(request.getParameter("semesterId"));
            Long departmentId = Long.parseLong(request.getParameter("departmentId"));

            Course course = new Course();
            course.setCourseName(courseName);
            Semester semester = new Semester();
            semester.setSemesterId(semesterId);
            course.setSemester(semester);
            Academic department = new Academic();
            department.setAcademicUnitId(departmentId);
            course.setDepartment(department);

            courseDao.createCourse(course);
            PrintWriter out = response.getWriter();
            out.println("Course inserted successfully!");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.println("Invalid input format!");
        }
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
            PrintWriter out = response.getWriter();
            out.println("Course updated successfully!");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.println("Invalid input format!");
        }
    }
}
