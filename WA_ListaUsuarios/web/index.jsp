<%-- 
    Document   : index
    Created on : Sep 28, 2024, 7:26:54 PM
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WA - Lista de Usuarios</title>
        <style>
            p, button {
                font-size: 1.5em;
            }
            input {
                font-size: 1em;
            }
        </style>
    </head>
    <body>
        <h1>Comprobación de credenciales</h1>
        
        <%
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
                    out.println(mensaje + "<br>");
            }
        %>
        
        <form action="s1" method="post">
            <p>Usuario: <input type="text" name="user"></p>
            <p>Contraseña: <input type="text" name="pwd"></p>
            <button type="submit" name="button">Iniciar Sesión</button>  
        </form>
            
    </body>
</html>
