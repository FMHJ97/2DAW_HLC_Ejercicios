<%-- 
    Document   : operacion
    Created on : 7 oct 2024, 14:36:23
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WA - Formulario 02: Calculadora</title>
    </head>
    <body>
        <h1>WA - Formulario 02: Calculadora</h1>
        <h2>Seleccione una operación</h2>
        <form action="s1" method="post">
            <input type="hidden" name="num1" value="<% out.println(request.getParameter("n1")); %>">
            <input type="hidden" name="num2" value="<% out.println(request.getParameter("n2")); %>">            
            <button type="submit" name="operacion" value="1">Suma</button>
            <button type="submit" name="operacion" value="2">Resta</button>
            <button type="submit" name="operacion" value="3">Multiplicacion</button>
            <button type="submit" name="operacion" value="4">Division</button>
            
        </form>
        
    </body>
</html>
