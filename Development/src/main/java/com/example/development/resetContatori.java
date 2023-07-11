package com.example.development;

import com.example.development.model.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "resetContatori", value = "/resetContatori")
public class resetContatori extends HttpServlet {
    Connection conn;

    VisiteDAO visiteDAO;

    @Override
    public void init() {

        conn = GenericDAO.getConnection();
        visiteDAO = new VisiteDAO(conn);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            request.getSession(false);

            HttpSession session = request.getSession(false);

            visiteDAO.resetVisite(); // Azzero i contatori

            // Restituisco la page per fare il refresh
            PrintWriter writer = response.getWriter();
            writer.print(response.encodeRedirectURL(request.getContextPath() + "/Amministratore"));
            writer.close();
            response.setStatus(200);

        } catch (IOException e) {
            response.setStatus(500);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public void destroy() {
        GenericDAO.closeConnection(conn);
    }
}
