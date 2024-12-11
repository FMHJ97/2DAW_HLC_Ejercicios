<%-- 
    Document   : index
    Created on : 11 dic 2024, 10:26:36
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("autenticado") != null) {
        request.getRequestDispatcher("foro.jsp").forward(request, response);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Examen HLC - DGT (Index)</title>
    </head>
    <body>
        <img src="img/logo.png" width="300px" height="150px"/>
        <h1>Inicio de Sesión</h1>
        <form action="s1" action="POST">
            <p>Usuario: <input type="text" name="user"></p>
            <p>Contraseña: <input type="password" name="pwd"></p>
            <button type="submit">Iniciar sesión</button>
        </form>
        <%
            if (request.getAttribute("msg") != null) {
            String msg = (String)request.getAttribute("msg");
            out.println("<br>");
            out.println(msg);
            }
        %>
    </body>
</html>
