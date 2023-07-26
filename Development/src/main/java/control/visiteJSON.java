package control;

import model.GenericDAO;
import model.VisiteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "visiteJSON", value = "/visiteJSON")
public class visiteJSON extends HttpServlet {
    Connection conn;
    VisiteDAO visiteDAO;

    @Override
    public void init() {
        conn = GenericDAO.getConnection();
        visiteDAO = new VisiteDAO(conn);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        // prende dal db tutte le Visite in formato JSON
        String visiteJSON = visiteDAO.getAllVisiteJSON();

        // invia al client l'array json con le viste
        PrintWriter out = response.getWriter();
        out.print(visiteJSON);
        out.close();

    }

    @Override
    public void destroy() {
        GenericDAO.closeConnection(conn);
    }
}
