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
@WebServlet(name = "s2", urlPatterns = {"/s2"})
public class s2 extends HttpServlet {

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

            HttpSession my_session = request.getSession();

            // Obtenemos el nombre (PK) del usuario logueado.
            Object[] registro = (Object[]) my_session.getAttribute("login");
            String autor = (String) registro[0];

            if (request.getParameter("logout") != null) {
                my_session.removeAttribute("login");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            if (request.getParameter("cancel") != null) {
                request.getRequestDispatcher("foro.jsp").forward(request, response);
            }

            if (request.getParameter("borrar") != null) {
                // Obtenemos el id del registro seleccionado.
                int id_registro = Integer.parseInt(request.getParameter("borrar"));

                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Creamos la consulta.
                    String sql = "SELECT * FROM mensaje WHERE id = " + id_registro;
                    ResultSet rs = instruccion.executeQuery(sql);
                    // Seleccionamos el registro obtenido y borramos.
                    rs.absolute(1);
                    rs.deleteRow();
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    // Redirigimos a la página anterior.
                    my_session.removeAttribute("msg2");
                    request.getRequestDispatcher("foro.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (request.getParameter("insertar") != null) {
                // Obtenemos los parámetros introducidos por el usuario.
                String mensaje = request.getParameter("mensaje");

                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Creamos la consulta.
                    String sql = "SELECT * FROM mensaje";
                    ResultSet rs = instruccion.executeQuery(sql);
                    // Procedemos a realizar la inserción.
                    rs.moveToInsertRow();
                    // La primary key es auto_increment => omitimos.
                    rs.updateString(2, autor);
                    rs.updateString(3, mensaje);
                    // Insertamos el nuevo registro.
                    rs.insertRow();
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    // Redirigimos a la página anterior.
                    my_session.removeAttribute("msg2");
                    request.getRequestDispatcher("foro.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (request.getParameter("editar") != null) {
                int id_registro = Integer.parseInt(request.getParameter("editar"));
                String mensaje = request.getParameter("mensaje");

                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Creamos la consulta.
                    String sql = "SELECT * FROM mensaje WHERE id = " + id_registro;
                    ResultSet rs = instruccion.executeQuery(sql);
                    if (rs.next()) {
                        rs.updateString("contenido", mensaje);
                        rs.updateRow();
                    }
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    // Redirigimos a la página anterior.
                    request.getRequestDispatcher("foro.jsp").forward(request, response);
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
