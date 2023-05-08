package com.example.cookies2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SessionManager", value = "/SessionManager")
public class SessionManager extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Il Banner è stato inviato:

        System.out.println("Handling the form");
        request.getSession();

        request.removeAttribute("formNeeded");
        request.setAttribute("updated",true);
        String origin = request.getHeader("referer");
        System.out.println("origin: " + origin);
        response.sendRedirect(origin);
    }
}
