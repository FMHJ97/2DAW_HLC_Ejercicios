<%-- 
    Document   : index
    Created on : 14 oct 2024, 13:15:38
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
        
        if (session.isNew()) {
            out.println("La primera vez que entras<br>");
            // El atributo creado en la variable sesión.
            // Los atributos permanecen en el servidor, NO en el navegador(cookie).
            session.setAttribute("nombre", "pepe");
        }
        else {
            out.println("La segunda vez que entras");
            String cadena = (String) session.getAttribute("nombre");
            out.println(cadena);
        }

        %>
    </body>
</html>
