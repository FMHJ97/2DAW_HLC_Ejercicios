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

            // Obtenemos los datos introducidos.
            String user = request.getParameter("user");
            String pwd = request.getParameter("pwd");

            // Mensaje de error de credenciales.
            if (user.isEmpty() || pwd.isEmpty()) {
                request.setAttribute("msg", "<span style='color:red;'>Usuario o clave incorrecta!</span>");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            // Realizamos consulta a la BD para verificar usuario.
            try {
                // Creamos el objeto conexion
                Connection conn = new ConnMysql().getConnection();
                // Creamos un objeto Statement
                Statement instruccion = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                // Creamos el query
                String sql = "SELECT * from usuario WHERE dni = '" + user + "' "
                        + "AND pass = '" + pwd + "'";
                ResultSet rs = instruccion.executeQuery(sql);

                // Si existe registro (existe usuario), creamos sesión
                // y redirigimos.
                if (rs.next()) {
                    // Guardamos los datos en un array.
                    Object[] registro = new Object[5];
                    registro[0] = rs.getString("dni");
                    registro[1] = rs.getString("nombre");
                    registro[2] = rs.getInt("puntos");
                    registro[3] = rs.getInt("admin");
                    registro[4] = rs.getString("pass");

                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();

                    // Creamos un atributo en la sesión con el array creado.
                    my_session.setAttribute("autenticado", registro);
                    
                    // Redirigimos.
                    // Si el usuario es admin (1), redirigimos a admin.jsp
                    if ((int) registro[3] == 1) {
                        request.getRequestDispatcher("admin.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("conductor.jsp").forward(request, response);
                    }
                } else {
                    // Mensaje de error de credenciales.
                    request.setAttribute("msg", "<span style='color:red;'>Usuario o clave incorrecta!</span>");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }

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
