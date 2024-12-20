/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnMysql;

/**
 *
 * @author DIURNO
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            try {
                // Creamos el objeto conexion
                Connection conn = new ConnMysql().getConnection();
                // Creamos un objeto Statement
                Statement instruccion = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                // Creamos el query
                String sql = "select * from t1";
                ResultSet result = instruccion.executeQuery(sql);
                while (result.next()) {
                    out.println("Id:" + result.getInt(1) + " ");
                    out.println("Nombre:" + result.getString(2) + " ");
                    out.println("Apellidos:" + result.getString(3));
                    out.println("<br>");
                }
                
                // Para borrar una fila.
                result.absolute(4); // Nos situamos sobre la fila deseada (Primera).
                result.deleteRow(); // Borramos.
                
                // Para actualizar un campo.
                result.absolute(3);
                result.updateString("Nombre", "Juana");
                result.updateRow();
                
                // Para insertar una fila.
                result.moveToInsertRow();
                result.updateInt(1, 4);
                result.updateString(2, "Salazar");
                result.updateString("Apellidos", "Cortés");
                result.insertRow();
                
                // Cerrar cada uno de los objetos utilizados
                result.close();
                instruccion.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet s1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet s1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(s1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(s1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
