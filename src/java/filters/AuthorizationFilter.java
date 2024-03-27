package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 *
 * @author Gyssagara
 */

@WebFilter(urlPatterns = {"/*"})
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false); // Don't create a new session if it doesn't exist
        String requestURI = request.getRequestURI();

        if (requestURI.endsWith("/LoginServlet") || requestURI.endsWith("/SignUpServlet") || requestURI.endsWith("/login.jsp")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Check if user is logged in
        if (session != null && session.getAttribute("role") != null) {
            String role = (String) session.getAttribute("role");
            // Check for user role and appropriate access
            if (role.equals("ADMIN") || (role.equals("STUDENT") && (requestURI.contains("/academic") || requestURI.equals("/application"))) ||
                (role.equals("TEACHER") && (requestURI.contains("/research") || requestURI.contains("/media")))) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        // If not logged in or unauthorized, redirect to login page
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    @Override
    public void destroy() {
        // Clean-up code, if needed
    }
}
