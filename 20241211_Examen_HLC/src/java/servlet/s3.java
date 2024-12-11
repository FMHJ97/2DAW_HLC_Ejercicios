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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.ConnMysql;

/**
 *
 * @author FMHJ97
 */
@WebServlet(name = "s3", urlPatterns = {"/s3"})
public class s3 extends HttpServlet {

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

            // Si realizamos un borrado.
            if (request.getParameter("borrar") != null) {

                // Obtenemos la matricula del coche (PK).
                String matri = request.getParameter("borrar");

                // Realizamos la inserción.
                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Creamos la consulta.
                    String sql = "SELECT * FROM coche WHERE matricula = '" + matri + "'";
                    ResultSet rs = instruccion.executeQuery(sql);
                    // Procedemos a realizar el borrado.
                    if (rs.next()) {
                        rs.deleteRow();
                    }
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    // Redirigimos a la página anterior.
                    request.getRequestDispatcher("conductor.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            // Si realizamos una inserción.
            if (request.getParameter("insert") != null) {

                // Obtenemos los datos introducidos.
                String matricula = request.getParameter("matricula");
                String modelo = request.getParameter("modelo");
                // Sacamos el dni del conductor.
                Object[] registro = (Object[]) my_session.getAttribute("autenticado");
                String dni = (String) registro[0];
                
                // Comprobamos si existe esa matrícula en la BBDD.
                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Creamos la consulta.
                    String sql = "SELECT * FROM coche WHERE matricula = '" + matricula + "'";
                    ResultSet rs = instruccion.executeQuery(sql);
                    
                    // Si existe un registro, significa que ya existe esa matrícula.
                    if (rs.next()) {
                        request.setAttribute("msg2", "<span style='color:red;'>Ya existe un coche con esa matrícula en la BD!</span>");
                        request.getRequestDispatcher("conductor.jsp").forward(request, response);
                    }
                    
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    
                    
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Realizamos la inserción.
                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Creamos la consulta.
                    String sql = "SELECT * FROM coche";
                    ResultSet rs = instruccion.executeQuery(sql);
                    // Procedemos a realizar la inserción.
                    rs.moveToInsertRow();
                    // Agregamos los datos.
                    rs.updateString("matricula", matricula);
                    rs.updateString("propietario", dni);
                    rs.updateString("modelo", modelo);
                    // Insertamos el nuevo registro.
                    rs.insertRow();
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    // Redirigimos a la página anterior.
                    request.getRequestDispatcher("conductor.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

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
