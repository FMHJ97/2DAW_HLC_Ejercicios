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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnMysql;
import java.sql.*;

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

            if (request.getParameter("editar") != null) {
                // Obtenemos el id del registro seleccionado.
                int id_registro = Integer.parseInt(request.getParameter("editar"));
            }
            
            // En caso de pulsar Editar.
            if (request.getParameter("editar") != null) {
                // Obtenemos el id del registro seleccionado.
                int id_registro = Integer.parseInt(request.getParameter("editar"));
                
                // Obtenemos los valores de los input.
                String nombre = request.getParameter("nombre");
                Integer nota = Integer.parseInt(request.getParameter("nota"));
                Date fecha_nac = Date.valueOf(request.getParameter("fecha_nac"));    
                
                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Obtenemos el registro a modificar.
                    String sql = "SELECT * FROM datos WHERE ID = " + id_registro;
                    ResultSet rs = instruccion.executeQuery(sql);
                    // Procedemos a modificar el registro.
                    rs.absolute(1);
                    rs.updateString(2, nombre);
                    rs.updateInt(3, nota);
                    rs.updateDate(4, fecha_nac);
                    rs.updateRow();
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    // Redirigimos a index.jsp
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            // En caso de pulsar Borrar.
            if (request.getParameter("borrar") != null) {
                // Obtenemos el id del registro seleccionado.
                int id_registro = Integer.parseInt(request.getParameter("borrar"));
                
                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Obtenemos el registro a eliminar.
                    String sql = "SELECT * FROM datos WHERE ID = " + id_registro;
                    ResultSet rs = instruccion.executeQuery(sql);
                    // Procedemos a eliminar el registro.
                    // Dado que solo devuelve un registro, borramos la primera fila.
                    rs.absolute(1);
                    rs.deleteRow();
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    // Redirigimos a index.jsp
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            // En caso de pulsar Insertar.
            if (request.getParameter("insertar") != null) {
                // Obtenemos los valores de los input.
                String nombre = request.getParameter("nombre");
                Integer nota = Integer.parseInt(request.getParameter("nota"));
                Date fecha_nac = Date.valueOf(request.getParameter("fecha_nac"));

                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Obtenemos todos los registros de la tabla.
                    String sql = "SELECT * FROM datos";
                    ResultSet rs = instruccion.executeQuery(sql);
                    // Situamos el cursor en la posición de insertar.
                    rs.moveToInsertRow();
                    // Comenzamos a asignar valores a cada columno de la tabla.
                    // Dado que ID es auto_increment, no tenemos que asignar nada.
                    rs.updateString(2, nombre);
                    rs.updateInt(3, nota);
                    rs.updateDate(4, fecha_nac);
                    // Procedemos a insertar.
                    rs.insertRow();
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    // Redirigimos a index.jsp
                    request.getRequestDispatcher("index.jsp").forward(request, response);
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
