/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author l11m02
 */
public class AgregarMulta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            //String id_vehiculo = request.getParameter("id_vehiculo");
            int DNI = Integer.parseInt(request.getParameter("DNI"));
            String multa = request.getParameter("multa");
            double monto = 0;

            //
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/multa?user=root&password=mysqladmin";
            Connection connect = DriverManager.getConnection(url);
            String query = "insert into detalle(DNI,multa,monto) VALUES(?,?,?)";
            PreparedStatement ps = connect.prepareStatement(query);

            if (multa.equalsIgnoreCase("Alta Velocidad")) {
                monto = 450;
            }
            if (multa.equalsIgnoreCase("Pasa la luz roja")) {
                monto = 300;
            }
            if (multa.equalsIgnoreCase("Estacionar zona pro")) {
                monto = 500;
            }
            if (multa.equalsIgnoreCase("Pico placa")) {
                monto = 320;
            }

            ps.setInt(1, DNI);
            ps.setString(2, multa);
            ps.setDouble(3, monto);
            ps.executeUpdate();

            JsonObject gson = new JsonObject();
            gson.addProperty("mensaje", "Multa agregado.");
            out.print(gson.toString());
        } catch (Exception e) {
            System.err.println("ERROR!");
            System.err.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
