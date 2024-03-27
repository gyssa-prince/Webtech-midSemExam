package controller;

import Model.Course;
import Model.CourseDef;
import dao.CourseDao;
import dao.CourseDefDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CourseDefController
 * @author Gyssagara
 */
@WebServlet("/CourseDefinitionController")
public class CourseDefController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourseDefDao courseDefinitionDao;
    private CourseDao courseDao;

    public void init() {
        courseDefinitionDao = new CourseDefDao();
        courseDao = new CourseDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Here you can add logic to retrieve data if needed
        // For example:
        // List<CourseDef> courseDefinitions = courseDefinitionDao.getAllCourseDefinitions();
        // Then you can forward the request to a JSP to display the data
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Delete Course Definition
            Long definitionId = Long.parseLong(request.getParameter("definitionId"));
            CourseDef courseDefinition = new CourseDef();
            courseDefinition.setId(definitionId);
            courseDefinitionDao.deleteCourseDefinition(courseDefinition);
            PrintWriter out = response.getWriter();
            out.println("Course Definition deleted successfully!");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.println("Invalid definition ID!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Insert Course Definition
            String description = request.getParameter("description");
            Long courseId = Long.parseLong(request.getParameter("courseId"));

            CourseDef courseDefinition = new CourseDef();
            courseDefinition.setDescription(description);
            Course course = courseDao.getCourseById(courseId);
            courseDefinition.setCourse(course);

            courseDefinitionDao.createCourseDefinition(courseDefinition);
            PrintWriter out = response.getWriter();
            out.println("Course Definition inserted successfully!");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.println("Invalid input format!");
        }
    }
}
