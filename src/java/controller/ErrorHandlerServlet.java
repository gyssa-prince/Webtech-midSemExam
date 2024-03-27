import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ErrorHandlerServlet
 * @author Gyssagara
 */

@WebServlet("/errorHandler")
public class ErrorHandlerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String AUTHOR = "Your Name";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Integer errorCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Error</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; }");
        out.println(".container { max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; background-color: #f9f9f9; }");
        out.println("h1 { color: #ff0000; }");
        out.println("p { color: #333333; }");
        out.println(".button { background-color: #4CAF50; border: none; color: white; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 4px 2px; cursor: pointer; border-radius: 5px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        
        if (errorCode != null) {
            switch (errorCode) {
                case 400:
                    out.println("<h1>400 - Bad Request</h1>");
                    out.println("<p>The request could not be understood by the server due to malformed syntax.</p>");
                    break;
                case 401:
                    out.println("<h1>401 - Unauthorized</h1>");
                    out.println("<p>This request requires user authentication.</p>");
                    break;
                case 403:
                    out.println("<h1>403 - Forbidden</h1>");
                    out.println("<p>You are not allowed to access this resource.</p>");
                    break;
                case 404:
                    out.println("<h1>404 - Not Found</h1>");
                    out.println("<p>The requested resource was not found on this server.</p>");
                    break;
                case 405:
                    out.println("<h1>404 - Not Found</h1>");
                    out.println("<p>The requested resource was not found on this server.</p>");
                    break;    
                case 500:
                    out.println("<h1>500 - Internal Server Error</h1>");
                    out.println("<p>An internal server error occurred.</p>");
                    break;
                default:
                    out.println("<h1>" + errorCode + " - Error</h1>");
                    out.println("<p>An unexpected error occurred.</p>");
                    break;
            }
        } else {
            out.println("<h1>Error</h1>");
            out.println("<p>An unexpected error occurred.</p>");
        }

        out.println("<a href='javascript:history.back()' class='button'>Go Back</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
