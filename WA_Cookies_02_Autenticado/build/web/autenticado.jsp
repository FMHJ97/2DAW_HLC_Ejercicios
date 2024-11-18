<%-- 
    Document   : autenticado
    Created on : 4 oct 2024, 12:39:53
    Author     : FMHJ97
--%>

<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Obtenemos el usuario.
    Usuario usuario = (Usuario) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Datos de Usuario Conectado</h1>
        
        <%
            out.println("Usuario conectado!<br>");
            out.println("<div>");
            out.println("<p>Usuario: " + usuario.getUsuario() + "</p>");
            out.println("<p>Password: " + usuario.getPassword() + "</p>");
            out.println("<p>Saldo: " + usuario.getSaldo() + "</p>");
            out.println("</div>");
        %>
        <br>
        <form action="s2" method="POST">
            <input type="submit" name="salir" value="Salir">
        </form>
    </body>
</html>
