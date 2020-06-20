package io.muzoo.ooc.webapp.basic.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
        requestDispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // authentication
        if (securityService.authenticate(username, password, request)) {
            response.sendRedirect("/");
        } else {
            error = "Username or Password incorrect. Please try again.";

            request.setAttribute("error", error);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
            requestDispatcher.include(request, response);
        }
    }

    @Override
    public String getPattern() {
        return "/login";
    }
}
