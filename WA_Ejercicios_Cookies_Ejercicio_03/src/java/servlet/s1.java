/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ListaVuelos;
import model.Vuelo;

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style>");
            out.println("table, th, td {border: 1px solid black; border-collapse: collapse;}");
            out.println("th, td {padding: 1.5em; text-align: center;}");
            out.println("th {background-color: lightcyan;}");
            out.println("</style>");
            out.println("<title>Ejercicios Cookies 2 - Ejercicio 03 (Servlet 1)</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Ejercicios Cookies 2 - Ejercicio 03 (Servlet 1)</h1>");

            // Obtenemos la sesión actual.
            HttpSession my_session = request.getSession();
            // Variable que almacenará la lista de vuelos.
            ListaVuelos vuelos = null;
            
            // Si es la primera vez que accedemos, se creará la lista.
            // En las futuras interacciones, obtenemos la lista guardada
            // en un atributo en la sesión actual. Simulando persistencia.
            if (my_session.getAttribute("vuelos") == null) {
                vuelos = new ListaVuelos();
            } else {
                vuelos = (ListaVuelos) my_session.getAttribute("vuelos");
            }
            
            // Obtenemos todas las cookies de la sesión actual.
            Cookie[] arrayCookie = request.getCookies();
            
            // Recorremos todos las cookies.
            // Si no existe la cookie que representa la sesión,
            // se creará.
            for (Cookie cookie : arrayCookie) {
                if (!cookie.getName().equalsIgnoreCase("login")) {
                    Cookie c = new Cookie("login", "on");
                    response.addCookie(c);
                }
            }
            
            // Creamos una tabla para mostrar los vuelos.
            out.println("<table>");
            out.println("<thead>");
            out.println("<th>Codigo</th><th>Fecha</th><th>Origen</th><th>Destino</th><th>Precio(€)</th>");
            out.println("</thead>");
            
            for (Vuelo vuelo : vuelos) {
                // Si existe la cookie login, subimos el precio de cada vuelo.
                for (Cookie cookie : arrayCookie) {
                    if (cookie.getName().equalsIgnoreCase("login")) {
                        vuelo.subirPrecio();
                    }
                }   
                
                out.println("<tr>");
                out.println("<td>" + vuelo.getCodigo() + "</td>");
                out.println("<td>" + vuelo.getFecha() + "</td>");
                out.println("<td>" + vuelo.getOrigen() + "</td>");
                out.println("<td>" + vuelo.getDestino() + "</td>");
                out.println("<td>" + vuelo.getPrecio() + "</td>");
                out.println("</tr>");
            }
            
            // Guardamos en un atributo de la sesión la lista de vuelos.
            // De esta manera, podemos simular la persistencia de los datos.
            my_session.setAttribute("vuelos", vuelos);
            
            out.println("</table>");
            out.println("<a href='index.jsp'><input type='button' value='Salir'></a>");
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
