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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author l11m02
 */
public class BorrarServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
       try {
           PrintWriter out = response.getWriter();
           String codigo = request.getParameter("codigo");
           //
           Class.forName("com.mysql.cj.jdbc.Driver");
           String url = "jdbc:mysql://localhost/multa?user=root&password=mysqladmin";
           Connection connect = DriverManager.getConnection(url);
           String query = "DELETE FROM detalle WHERE codigo = ?";
           PreparedStatement ps = connect.prepareStatement(query);
           ps.setInt(1, Integer.parseInt(codigo));
           ps.executeUpdate();
           
           JsonObject gson = new JsonObject();
           gson.addProperty("mensaje", "Multa borrado.");
           out.print(gson.toString());
        } catch(Exception e) {
            System.err.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
