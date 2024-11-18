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
            
            // Obtenemos la elección elegida.
            int opcion = Integer.parseInt(request.getParameter("boton"));
            // Obtenemos los valores de los campos.
            String nombre = request.getParameter("nombre");
            String valor = request.getParameter("valor");
            
            // Array Cookie que almacena todas las cookies activas en la web.
            Cookie[] array = request.getCookies();
            
            // Creamos una variable de sesión para mostrar el mensaje.
            HttpSession mySession = request.getSession();
            
            switch (opcion) {
                // Crear Cookie.
                case 1:
                    Cookie c = new Cookie(nombre, valor);
                    c.setMaxAge(3600);
                    response.addCookie(c);
                    // Volvemos al index.
                    mySession.setAttribute("msg", "Cookie creada correctamente!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                // Visualizar Cookie.
                case 2:
                    for (Cookie cookie : array) {
                        if(cookie.getName().equalsIgnoreCase(nombre)) {
                            // Volvemos al index.
                            mySession.setAttribute("msg", "El valor de la Cookie " + cookie.getName() + " es " + cookie.getValue());
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        }
                    }
                    break;
                // Modificar Cookie.
                case 3:
                    for (Cookie cookie : array) {
                        // Buscamos la cookie a modificar (por nombre).
                        if(cookie.getName().equalsIgnoreCase(nombre)) {
                            // Le asignamos un nuevo valor.
                            cookie.setValue(valor);
                            // Volvemos a enviar la nueva cookie.
                            response.addCookie(cookie);
                            // Volvemos al index.
                            mySession.setAttribute("msg", "El nuevo valor de la Cookie " + cookie.getName() + " es " + cookie.getValue());
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        }
                    }
                    break;
                // Eliminar Cookie.
                case 4:
                    for (Cookie cookie : array) {
                        // Buscamos la cookie a eliminar (por nombre).
                        if(cookie.getName().equalsIgnoreCase(nombre)) {
                            // Eliminamos la cookie estableciendo una expiración.
                            cookie.setMaxAge(0);
                            // Volvemos a enviar la nueva cookie.
                            response.addCookie(cookie);
                            // Volvemos al index.
                            mySession.setAttribute("msg", "La Cookie " + cookie.getName() + " ha sido eliminada correctamente!");
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        }
                    }
                    break;
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
