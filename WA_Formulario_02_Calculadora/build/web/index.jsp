<%-- 
    Document   : index
    Created on : 7 oct 2024, 14:21:17
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
        <form action="operacion.jsp" method="post">
            <h2>Introduzca 2 números</h2>
            <p>Número 1: <input type="number" name="n1"></p>
            <p>Número 2: <input type="number" name="n2"></p>
            <input type="submit" name="next" value="Siguiente">
        </form>
    </body>
</html>
