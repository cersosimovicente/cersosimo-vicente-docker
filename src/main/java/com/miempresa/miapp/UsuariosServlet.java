package com.miempresa.miapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Statement;


// Mapea la URL /usuarios a este Servlet
@WebServlet("/usuarios")
public class UsuariosServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html>");
            out.println("<head><title>Usuarios - Mi App</title></head>");
            out.println("<body>");
            out.println("<h1>Usuarios en MySQL</h1>");

            // Conectar a mysql-container y consultar la tabla usuarios
            try (Connection conn = ConexionMySQL.obtener();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(
                            "SELECT id, nombre, email, creado_en FROM usuarios")) {

                out.println("<table border='1' cellpadding='6'>");
                out.println("<tr><th>ID</th><th>Nombre</th>" +
                        "<th>Email</th><th>Creado</th></tr>");

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("id") + "</td>");
                    out.println("<td>" + rs.getString("nombre") + "</td>");
                    out.println("<td>" + rs.getString("email") + "</td>");
                    out.println("<td>" + rs.getTimestamp("creado_en") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");

            } catch (Exception e) {
                out.println("<p style='color:red'>Error de conexión: "
                        + e.getMessage() + "</p>");
            }

            out.println("</body></html>");
        }
    }

}
