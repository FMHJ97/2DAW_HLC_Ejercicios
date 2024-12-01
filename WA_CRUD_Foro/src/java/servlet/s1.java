/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ConnMysql;
import java.sql.*;

/**
 *
 * @author FMHJ97
 */
@WebServlet(name = "s1", urlPatterns = {"/s1"})
public class s1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            // Obtenemos la sesión actual.
            HttpSession my_session = request.getSession();
            
            // Si existe una sesión Login, redirigimos a foro.
            if (my_session.getAttribute("login") != null) {
                request.getRequestDispatcher("foro.jsp").forward(request, response);
            }
            
            // Sacamos los datos introducidos.
            String user = (String) request.getParameter("user");
            String pwd = (String) request.getParameter("pwd");

            // Realizamos consulta a la BD para vertificar usuario.
            try {
                // Creamos el objeto conexion
                Connection conn = new ConnMysql().getConnection();
                // Creamos un objeto Statement
                Statement instruccion = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                // Creamos el query
                String sql = "SELECT * from usuario WHERE usuario = '" + user 
                        + "' AND pass = '" + pwd + "'";
                ResultSet rs = instruccion.executeQuery(sql);
                // Si existe registro (existe usuario), creamos sesión
                // y redirigimos.
                if (rs.next()) {
                    Object[] registro = new Object[2];
                    registro[0] = rs.getString(1);
                    registro[1] = rs.getInt(3);
                    // Guardamos en la sesión los datos.
                    my_session.setAttribute("login", registro);
                    request.getRequestDispatcher("foro.jsp").forward(request, response);
                }
                // Cerrar cada uno de los objetos utilizados
                rs.close();
                instruccion.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

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
