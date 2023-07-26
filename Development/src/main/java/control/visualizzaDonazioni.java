package control;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "visualizzaDonazioni", value = "/visualizzaDonazioni")
public class visualizzaDonazioni extends HttpServlet {
    Connection conn;

    DonazioneDAO donazioneDAO;

    @Override
    public void init() {

        conn = GenericDAO.getConnection();
        donazioneDAO = new DonazioneDAO(conn);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        request.getSession(false);

        HttpSession session = request.getSession(false);

        String donazioni = donazioneDAO.getDonazioniByYearJSON(2023);   //Donazioni dell'anno corrente

        // Scrivo la risposta come array Json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            /*JsonArray array = new JsonArray();
            Gson gson = new Gson();
            array.add(gson.toJson(donazioni));
            out.println(array);
            out.flush();*/
            out.println(donazioni);
            out.flush();
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
