/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Consulta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/multa?user=root&password=mysqladmin";
            Connection connect = DriverManager.getConnection(url);
            Statement statement = connect.createStatement();
            String query = "select*from detalle";
            ResultSet resultSet = statement.executeQuery(query);

            JsonArray jarry = new JsonArray();
            JsonObject gson = new JsonObject();
            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                int DNI = resultSet.getInt("DNI");
                String multa = resultSet.getString("multa");
                double monto = resultSet.getDouble("monto");

                gson = new JsonObject();
                gson.addProperty("codigo", codigo);
                gson.addProperty("DNI", DNI);
                gson.addProperty("multa", multa);
                gson.addProperty("monto", monto);

                jarry.add(gson);
            }
            out.print(jarry.toString());

        } catch (Exception e) {
            System.err.println("ERROR!");
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
