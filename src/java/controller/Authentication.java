package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Authentication
 * @author Gyssagara
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class Authentication implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        if (requestURI.contains("/assets/") || requestURI.contains("Adventist%20University%20of%20Central%20Africa_files")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);

        // Check if user is authenticated
        if (session != null && session.getAttribute("user") != null) {
            // User is authenticated, allow access to all pages
            String role = (String) session.getAttribute("user");

            // Allow access to index.html and logout for all roles
            if (requestURI.endsWith("indexADMIN.html") || requestURI.endsWith("/logout")) {
                chain.doFilter(request, response);
                return;
            } else {
                switch (role) {
                    case "ADMIN":
                        chain.doFilter(request, response);
                        return;
                        
                    case "TEACHER":
                        if (requestURI.endsWith("Service%20Charter.html") || requestURI.endsWith("AUCA%20Journal.html")) {
                            chain.doFilter(request, response);
                            return;
                        } else {
                            httpResponse.sendRedirect("indexADMIN.html");
                        }
                        return;
                        
                    case "STUDENT":
                        if (requestURI.endsWith("AcademicCalender.html") || requestURI.endsWith("Apply.html")) {
                            chain.doFilter(request, response);
                            return;
                        } else {
                            httpResponse.sendRedirect("indexADMIN.html");
                            return;
                        }
                }
            }
        } else {
            // Case when user is not authenticated
            if ((requestURI.contains("login.jsp") || requestURI.contains("signup.jsp")
                    || requestURI.endsWith("/login") || requestURI.contains("/register") || requestURI.endsWith("/logout"))) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
                return;
            }    
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
