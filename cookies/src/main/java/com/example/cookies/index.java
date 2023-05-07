package com.example.cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "index", value="/index")
public class index extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process_request(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process_request(request, response);
    }
    protected void process_request(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // include the request to the JSP page
        request.getRequestDispatcher("/homepage.jsp").include(request, response);
        String check = (String) request.getAttribute("CookiesVerified");
        System.out.println("check: " + check);
        if (check == null) {
            System.out.println("DIOC");
            request.getRequestDispatcher("/SessionManager").forward(request, response);
        }
        System.out.println("Control ready");
    }
}