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
        <h1>Saldo del Usuario Autenticado</h1>
        
        <%
            Usuario usuario = (Usuario) session.getAttribute("user");
            out.println("<p>Usuario: " + usuario.getUsuario());
            out.println("<p>Saldo: " + usuario.getSaldo());
        %>
        <form action="s3" method="POST">
            <input type="submit" name="operation" value="-">
            <input type="submit" name="operation" value="+">
        </form>
        <form action="autenticado.jsp" method="POST">
            <input type="submit" name="salir" value="Volver atrás">
        </form>
    </body>
</html>
