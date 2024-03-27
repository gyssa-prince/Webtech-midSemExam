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
 * Servlet implementation class for Semester controler
 * Author: Gyssagara
 */
@WebServlet("/SemesterController")
public class SemesterController extends HttpServlet {
    private SemesterDao semesterDao;

    @Override
    public void init() throws ServletException {
        semesterDao = new SemesterDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doDelete(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Add a new Semester
        String semesterName = request.getParameter("semesterName");
        Date startingDate = null;
        Date endDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startingDate = dateFormat.parse(request.getParameter("startingDate"));
            endDate = dateFormat.parse(request.getParameter("endDate"));
        } catch (ParseException e) {
            // Handle parsing error
            e.printStackTrace();
        }
        Semester semester = new Semester(semesterName, startingDate, endDate);
        semesterDao.createSemester(semester);

        PrintWriter out = response.getWriter();
        out.println("Semester added successfully!");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Remove a Semester
        Long semesterId = Long.parseLong(request.getParameter("semesterId"));

        Semester semester = new Semester();
        semester.setSemesterId(semesterId);
        semesterDao.DeleteSemester(semester);

        PrintWriter out = response.getWriter();
        out.println("Semester removed successfully!");
    }
}
