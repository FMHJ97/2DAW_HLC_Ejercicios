<%-- 
    Document   : index
    Created on : 4 nov 2024, 14:43:58
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>WA - Gestión de Cookies</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Gestión de Cookies</h1>
        <div>
            <h2>Instrucciones</h2>
            <ul>
                <li>En el caso "Crear" Cookie, pasamos nombre y valor.</li>
                <li>En el caso "Visualizar" Cookie, pasamos el nombre de la cookie.</li>
                <li>En el caso "Modificar" Cookie, se necesita el nombre y el nuevo valor.</li>
                <li>En el caso "Eliminar" Cookie, se necesita el nombre de la cookie que se quiere eliminar.</li>
            </ul>
        </div><br>
        <form action="s1" method="POST">
            <p>Nombre de la Cookie: <input type="text" name="nombre"></p>
            <p>Valor de la Cookie: <input type="text" name="valor"></p>
            <button type="submit" name="boton" value="1">Crear</button>
            <button type="submit" name="boton" value="2">Visualizar</button>
            <button type="submit" name="boton" value="3">Modificar</button>
            <button type="submit" name="boton" value="4">Eliminar</button>
        </form>
        
        <%
            String msg = (String) session.getAttribute("msg");
            if (msg != null) out.println("<br>" + msg);
        %>
        
    </body>
</html>
