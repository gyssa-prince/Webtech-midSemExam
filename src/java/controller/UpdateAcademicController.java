package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import Model.Academic;
import Model.EAcademic;
import dao.AcademicDao;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Gyssagara
 */

@WebServlet("/UpdateAcademicUnitController")
public class UpdateAcademicController extends HttpServlet {
    
    private AcademicDao academicUnitDao;
    
    @Override
    public void init() throws ServletException {
        academicUnitDao = new AcademicDao(); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For simplicity, handle GET requests the same way as PUT requests
        doPut(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Update Academic Unit
        try {
            Long academicUnitId = Long.parseLong(request.getParameter("academicUnitId"));
            String academicUnitName = request.getParameter("academicUnitName");
            EAcademic academicUnitType = EAcademic.valueOf(request.getParameter("academicUnitType"));
            Long parentId =  Long.parseLong(request.getParameter("parentId"));
            Academic theAcademicUnit = new Academic();
            theAcademicUnit.setAcademicUnitId(parentId);
            Academic academicUnit = new Academic(academicUnitId, academicUnitName, academicUnitType, theAcademicUnit);
            academicUnitDao.updateAcademicUnit(academicUnit);
            sendResponse(response, "Academic Unit updated successfully!");
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
