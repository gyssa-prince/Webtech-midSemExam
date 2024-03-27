package controller;

import Model.Course;
import Model.CourseDef;
import dao.CourseDao;
import dao.CourseDefDao;
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

@WebServlet("/UpdateCourseDefinitionController")
public class UpdateCourseDefController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourseDefDao courseDefinitionDao;
    private CourseDao courseDao;

    @Override
    public void init() {
        courseDefinitionDao = new CourseDefDao();
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
            Long definitionId = Long.parseLong(request.getParameter("id"));
            String description = request.getParameter("description");
            Long courseId = Long.parseLong(request.getParameter("courseId"));
            
            CourseDef courseDefinition = new CourseDef();
            courseDefinition.setId(definitionId);
            courseDefinition.setDescription(description);
            
            Course course = courseDao.getCourseById(courseId);
            courseDefinition.setCourse(course);

            courseDefinitionDao.updateCourseDefinition(courseDefinition);      
            
            sendResponse(response, "Course Definition updated successfully!");
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
