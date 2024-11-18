<%-- 
    Document   : autenticado
    Created on : 4 oct 2024, 12:39:53
    Author     : FMHJ97
--%>

<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Datos de Usuario Conectado</h1>
        
        <%
            Usuario usuario = (Usuario) session.getAttribute("user");
            out.println("Usuario conectado!<br>");
            out.println("<p>Usuario: " + usuario.getUsuario());
        %>
        <form action="saldo.jsp" method="POST">
            <input type="submit" name="show" value="Ver Saldo">
        </form>
        <br>
        <form action="s2" method="POST">
            <input type="submit" name="salir" value="Salir">
        </form>
    </body>
</html>
