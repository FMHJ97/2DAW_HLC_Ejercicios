<%-- 
    Document   : index
    Created on : Nov 10, 2024, 1:20:26 PM
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Cookie[] arrayCookies = request.getCookies();
    
    String user=""; String asiento=""; String menu="";
    
    if (arrayCookies != null) {
        for (Cookie cookie : arrayCookies) {
            if (cookie.getName().equalsIgnoreCase("user")) user = cookie.getValue();
            if (cookie.getName().equalsIgnoreCase("asiento")) asiento = cookie.getValue();
            if (cookie.getName().equalsIgnoreCase("menu")) menu = cookie.getValue();
            if (cookie.getName().equalsIgnoreCase("aeropuertos")) {
                cookie.getValue()
            }
        }
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejercicios Cookies 2 - Ejercicio 01</title>
    </head>
    <body>
        <h1>Preferencias de vuelo</h1>
        <form action="s1" method="POST">
            <p>Nombre del usuario: <input type="text" name="user"
                                          value="<% if (!user.isBlank()) out.print(user); %>">
            </p>
            <p>Asiento:
                <select name="asiento">
                    <option value="pasillo" <% if (!asiento.isBlank()&&asiento.equalsIgnoreCase("pasillo")) out.print("selected"); %>>Pasillo</option>
                    <option value="ventanilla" <% if (!asiento.isBlank()&&asiento.equalsIgnoreCase("ventanilla")) out.print("selected"); %>>Ventanilla</option>
                    <option value="centro" <% if (!asiento.isBlank()&&asiento.equalsIgnoreCase("centro")) out.print("selected"); %>>Centro</option>
                </select>
            </p>
            <p>Menú:
                <select name="menu">
                    <option value="vegetariano" <% if (!menu.isBlank()&&menu.equalsIgnoreCase("vegetariano")) out.print("selected"); %>>Vegetariano</option>
                    <option value="no_vegetariano" <% if (!menu.isBlank()&&menu.equalsIgnoreCase("no_vegetariano")) out.print("selected"); %>>No Vegetariano</option>
                    <option value="diabetico" <% if (!menu.isBlank()&&menu.equalsIgnoreCase("diabetico")) out.print("selected"); %>>Diabético</option>
                    <option value="infantil" <% if (!menu.isBlank()&&menu.equalsIgnoreCase("infantil")) out.print("selected"); %>>Infantil</option>
                </select>
            </p>
            <p>Aeropuertos:
                <input type="checkbox" name="aeropuertos[]" value="LHR"><label>Londres</label>
                <input type="checkbox" name="aeropuertos[]" value="CDG"><label>París</label>
                <input type="checkbox" name="aeropuertos[]" value="CIA"><label>Roma</label>
                <input type="checkbox" name="aeropuertos[]" value="IBZ"><label>Ibiza</label>
                <input type="checkbox" name="aeropuertos[]" value="SIN"><label>Singapur</label>
                <input type="checkbox" name="aeropuertos[]" value="HKG"><label>Hong Kong</label>
                <input type="checkbox" name="aeropuertos[]" value="MLA"><label>Malta</label>
                <input type="checkbox" name="aeropuertos[]" value="BOM"><label>Bombay</label>
            </p>
            <input type="submit" name="enviar" value="Enviar">
        </form>
    </body>
</html>
