package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AcademicDao;
import java.io.IOException;
import java.io.PrintWriter;
import Model.Academic;
import Model.EAcademic;

/**
 * Servlet implementation class AcademicController
 * @author Gyssagara
 */
@WebServlet("/AcademicUnitController")
public class AcademicController extends HttpServlet {

    private AcademicDao academicUnitDao;

    @Override
    public void init() throws ServletException {
        academicUnitDao = new AcademicDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Here you can add logic to retrieve data if needed
        // For example:
        // List<Academic> academicUnits = academicUnitDao.getAllAcademics();
        // Then you can forward the request to a JSP to display the data
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String academicUnitName = request.getParameter("academicUnitName");
            EAcademic academicUnitType = EAcademic.valueOf(request.getParameter("academicUnitType"));
            String parent = request.getParameter("parentId");
            Academic theAcademicUnit = null;
            if (parent != null && !parent.isEmpty()) {
                Long parentId = Long.parseLong(parent);
                theAcademicUnit = new Academic();
                theAcademicUnit.setAcademicUnitId(parentId);
            }

            Academic academicUnit = new Academic(academicUnitName, academicUnitType, theAcademicUnit);
            academicUnitDao.createAcademic(academicUnit);

            PrintWriter out = response.getWriter();
            out.println("Academic Unit inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting Academic Unit: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long academicUnitId = Long.parseLong(request.getParameter("academicUnitId"));

            Academic academicUnit = new Academic();
            academicUnit.setAcademicUnitId(academicUnitId);
            academicUnitDao.deleteAcademic(academicUnit);

            PrintWriter out = response.getWriter();
            out.println("Academic Unit deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting Academic Unit: " + e.getMessage());
        }
    }
}
