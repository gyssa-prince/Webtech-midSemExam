package controller;

import Model.Semester;
import dao.SemesterDao;

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

/**
 *
 * @author Gyssagara
 */

@WebServlet("/UpdateSemesterController")
public class UpdateSemesterController extends HttpServlet {

    private SemesterDao semesterDao;

    @Override
    public void init() throws ServletException {
        semesterDao = new SemesterDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For simplicity, handle GET requests the same way as PUT requests
        doPut(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Update Semester
            Long semesterId = Long.parseLong(request.getParameter("semesterId"));
            String semesterName = request.getParameter("semesterName");
            Date startingDate = null;
            Date endDate = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            startingDate = dateFormat.parse(request.getParameter("startingDate"));
            endDate = dateFormat.parse(request.getParameter("endDate"));

            Semester semester = new Semester(semesterId, semesterName, startingDate, endDate);
            semesterDao.updateSemester(semester);

            sendResponse(response, "Semester updated successfully!");
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
